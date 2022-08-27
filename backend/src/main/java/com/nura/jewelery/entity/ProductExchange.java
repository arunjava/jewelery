package com.nura.jewelery.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nura.jewelery.dto.ProductExchangeDTO;
import com.nura.jewelery.entity.product.Product;
import com.nura.jewelery.entity.scheme.Scheme;
import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tx_prod_exchange", schema = Constants.SCHEMA_JEWEL)

@NamedNativeQuery(query = "SELECT SUM(TXPE.EXCHANGE_VAL) as exchangeVal ,PRODUCT.PRODUCT_ID as productID, TXPE.CUST_ID as customerID, "
		+ " PRODUCT.PRODUCT_NAME as productName,"
		+ " TXPE.EXCHANGE_UOM as uomID, UOM.UOM_DESC  as uomDesc  FROM JEWEL.TX_PROD_EXCHANGE AS TXPE   "
		+ "LEFT JOIN JEWEL.MS_PRODUCT AS PRODUCT ON PRODUCT.PRODUCT_ID = TXPE.EXCHANGE_PROD  LEFT JOIN  "
		+ "	JEWEL.MS_UOM AS UOM ON TXPE.EXCHANGE_UOM = UOM.UOM_ID  GROUP BY  "
		+ "	TXPE.EXCHANGE_PROD, TXPE.CUST_ID, PRODUCT.PRODUCT_ID, PRODUCT.PRODUCT_NAME , TXPE.EXCHANGE_UOM, UOM.UOM_DESC"
		+ "  HAVING TXPE.CUST_ID = :customerID ORDER BY PRODUCT.PRODUCT_ID" , resultSetMapping = "ProductExchangeDTO", name = "ProductExchangeDTO")
@SqlResultSetMapping(name = "ProductExchangeDTO", classes = @ConstructorResult(
		targetClass = ProductExchangeDTO.class,
		columns = {
				@ColumnResult(name = "exchangeVal" , type = Double.class),
				@ColumnResult(name = "productID", type = Long.class),
				@ColumnResult(name = "customerID", type = Long.class),
				@ColumnResult(name = "productName", type = String.class),
				@ColumnResult(name = "uomID", type = Long.class),
				@ColumnResult(name = "uomDesc", type = String.class)
		}))
//Below for entity mapping
//@SqlResultSetMapping(name = "ProductExchangeDTO", entities = @EntityResult(
//		entityClass = ProductExchangeDTO.class,
//		fields = {
//				@FieldResult(name = "exchangeVal", column = "exchangeVal"),
//				@FieldResult(name = "customerID", column = "customerID"),
//				@FieldResult(name = "productID", column = "productID"),
//				@FieldResult(name = "productName", column = "productName"),
//				@FieldResult(name = "uomID", column = "uomID"),
//				@FieldResult(name = "uomDesc", column = "uomDesc")
//		}))
public class ProductExchange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5100361939001752860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exchange_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "scheme_id")
	private Scheme schemeID;

	@Column(name = "cust_id")
	private long customerID;

	@Column(name = "exchange_val")
	private double exchangeVal;

	@ManyToOne
	@JoinColumn(name = "exchange_uom")
	private UOM exchangeUOM;

	@ManyToOne
	@JoinColumn(name = "exchange_prod")
	private Product exchangeProduct;

	@Column(name = "invoice_id")
	private String invoiceID;

	@JsonIgnore
	@Embedded
	private ProbeClass probeClass = new ProbeClass();

}
