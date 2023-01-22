package com.nura.jewelery.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nura.jewelery.dto.sales.SalesDTO;
import com.nura.jewelery.entity.ProductExchange;
import com.nura.jewelery.entity.offers.Offer;
import com.nura.jewelery.entity.product.Pricing;
import com.nura.jewelery.entity.sales.Sales;
import com.nura.jewelery.entity.scheme.CustomerScheme;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.entity.uom.UOMConversion;
import com.nura.jewelery.exception.ApplicationException;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.mapper.SalesMapper;
import com.nura.jewelery.repository.CustomerSchemeRepository;
import com.nura.jewelery.repository.PricingRepository;
import com.nura.jewelery.repository.SalesRepository;
import com.nura.jewelery.repository.UOMConversionRepository;
import com.nura.jewelery.repository.UOMRepository;
import com.nura.jewelery.service.ProductExchangeService;
import com.nura.jewelery.service.SalesService;
import com.nura.jewelery.service.SchemeService;
import com.nura.jewelery.utils.Constants;
import com.nura.jewelery.utils.ExpressionParserUtil;
import com.nura.jewelery.utils.IDGenerator;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SalesRepository salesRepo;
	@Autowired
	private UOMRepository uomRepo;
	@Autowired
	private UOMConversionRepository uomConversionRepo;
	@Autowired
	private PricingRepository pricingRepo;
	@Autowired
	private CustomerSchemeRepository customerSchemeRepo;
	@Autowired
	private SalesMapper salesMapper;
	@Autowired
	private SchemeService schemeService;
	@Autowired
	private ProductExchangeService productExchangeService;

	@Override
	public Sales saveSales(SalesDTO salesDTO) {
		double salesAmt;
		double qty = salesDTO.getQty();
		salesDTO.setInvoiceNumber(IDGenerator.getSalesTxID());
		Sales sale = salesMapper.dtoTODomain(salesDTO);
		Optional<UOM> uom = uomRepo.findById(sale.getUom().getId());

		if (uom.isPresent()) {
			Pricing pricing = pricingRepo.getLatestPricingForProduct(sale.getProduct().getProductId());
			if (pricing != null) {
				if (pricing.getUom().getId().equals(uom.get().getId())) {
					salesAmt = qty * salesDTO.getSellingPrice();
					sale.setProfitAmt(salesAmt - (qty * salesDTO.getCostPrice()));
				} else {
					UOMConversion uomConversion = uomConversionRepo.getConversion(sale.getUom().getId(),
							pricing.getUom().getId());
					salesAmt = qty * salesDTO.getSellingPrice() * uomConversion.getConversionVal();
					sale.setProfitAmt(salesAmt - (qty * salesDTO.getCostPrice()));
				}

			} else {
				salesAmt = qty * salesDTO.getSellingPrice();
				sale.setProfitAmt(salesAmt - (qty * salesDTO.getCostPrice()));
			}
			if (salesDTO.getCustomerSchemeID() > 0) {
				CustomerScheme custScheme = customerSchemeRepo.getCustomerSchemeBsdOnID(salesDTO.getCustomerSchemeID());
				if (custScheme != null) {
					if (custScheme.isActive()) {
						offerCalculation(custScheme, sale);

						ProductExchange prodExchange = new ProductExchange();
						prodExchange.setCustomerID(salesDTO.getCustID());
						prodExchange.setExchangeProduct(sale.getProduct());
						prodExchange.setExchangeUOM(uom.get());
						prodExchange.setSchemeID(custScheme.getScheme());
						prodExchange.setExchangeVal(sale.getQty());
						prodExchange.setInvoiceID(sale.getInvoiceNumber());
						productExchangeService.saveProductExchange(prodExchange);

						custScheme.setActive(false);
						customerSchemeRepo.save(custScheme);
					} else {
						throw new ApplicationException("Selected scheme is not active");
					}
				}
			}
			sale.setCgstCharges(salesDTO.getCgst());
			sale.setSgstCharges(salesDTO.getSgst());
			sale.setGstCharges(salesDTO.getCgst() + salesDTO.getSgst());
			salesAmt += sale.getWastageCharges() + sale.getMakingCharges() + sale.getGstCharges();
			sale.setSoldAmt(salesAmt);
			return salesRepo.save(sale);
		} else {
			throw new NotFoundException("Invalid UOM selected id ::" + sale.getUom().getId());
		}
	}

	@Override
	public Sales preCalculate(SalesDTO salesDTO) {
		double salesAmt;
		double qty = salesDTO.getQty();
		salesDTO.setInvoiceNumber(IDGenerator.getSalesTxID());
		Sales sale = salesMapper.dtoTODomain(salesDTO);
		Optional<UOM> uom = uomRepo.findById(sale.getUom().getId());
		if (uom.isPresent()) {
			Pricing pricing = pricingRepo.getLatestPricingForProduct(sale.getProduct().getProductId());
			if (pricing != null) {
				if (pricing.getUom().getId().equals(uom.get().getId())) {
					salesAmt = qty * salesDTO.getSellingPrice();
					sale.setProfitAmt(salesAmt - (qty * salesDTO.getCostPrice()));
				} else {
					UOMConversion uomConversion = uomConversionRepo.getConversion(sale.getUom().getId(),
							pricing.getUom().getId());
					salesAmt = qty * salesDTO.getSellingPrice() * uomConversion.getConversionVal();
					sale.setProfitAmt(salesAmt - (qty * salesDTO.getCostPrice()));
				}

			} else {
				salesAmt = qty * salesDTO.getSellingPrice();
				sale.setProfitAmt(salesAmt - (qty * salesDTO.getCostPrice()));
			}

			salesAmt += sale.getWastageCharges() + sale.getMakingCharges();
			sale.setSoldAmt(salesAmt);

			return sale;
		} else {
			throw new NotFoundException("Invalid UOM selected id ::" + sale.getUom().getId());
		}
	}

	private void offerCalculation(CustomerScheme custScheme, Sales sale) {
		List<Offer> offers = schemeService.getOffersBsdOnSchemeID(custScheme.getScheme().getId());
		offers.forEach(offer -> {
			switch (Constants.OFFER_TYPES.valueOf(offer.getApplicableOn())) {
			case MAKING_CHARGES:
				sale.setMakingCharges(
						(double) ExpressionParserUtil.parseExp(offer.getExpression(), "" + sale.getQty()));
				break;
			case MONEY:
				break;
			case WASTAGE_CHARGES:
				sale.setWastageCharges(
						(double) ExpressionParserUtil.parseExp(offer.getExpression(), "" + sale.getQty()));
				break;
			case WEIGHT:
				double newQty = sale.getQty()
						+ (double) ExpressionParserUtil.parseExp(offer.getExpression(), "" + sale.getQty());
				sale.setQty(newQty);
				break;
			default:
				break;
			}
		});
	}

	@Override
	public List<Sales> getSalesBsdOnCutomerID(long custID) {
		Sort sort = Sort.by(Sort.Direction.DESC, "txnDate");
		return salesRepo.getSalesBsdOnCustomerID(custID, sort);
	}

}
