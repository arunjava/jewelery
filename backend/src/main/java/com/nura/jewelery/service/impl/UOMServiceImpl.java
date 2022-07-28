package com.nura.jewelery.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.entity.uom.UOMCategory;
import com.nura.jewelery.exception.AlreadyExistException;
import com.nura.jewelery.exception.ApplicationException;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.repository.UOMCategoryRepository;
import com.nura.jewelery.repository.UOMRepository;
import com.nura.jewelery.service.UOMService;

@Service
@Transactional
public class UOMServiceImpl implements UOMService {

	@Autowired
	private UOMRepository uomRepo;

	@Autowired
	private UOMCategoryRepository uomCatRepo;

	@Override
	public UOM save(UOM uom) {
		if (uom.getUomCategory() != null) {
			findByUOMCatID(uom.getUomCategory().getId());
			return uomRepo.save(uom);
		} else {
			throw new ApplicationException("UOM Category missing for UOM save option");
		}
	}

	@Override
	public UOM findByID(long id) {
		Optional<UOM> uom = uomRepo.findById(id);
		if (uom.isEmpty()) {
			throw new NotFoundException("UOM not found");
		}
		return uom.get();
	}

	@Override
	public UOMCategory findByUOMCatID(long id) throws NotFoundException {
		Optional<UOMCategory> uomCat = uomCatRepo.findById(id);
		if (uomCat.isEmpty()) {
			throw new NotFoundException("UOM Category Not Found");
		}

		return uomCat.get();
	}

	@Override
	public UOMCategory saveUOMCategory(UOMCategory uomCategory) {
		if (!uomCatRepo.findByName(uomCategory.getUomCat()).isEmpty()) {
			throw new AlreadyExistException("UOM Category already exist");
		}

		return uomCatRepo.save(uomCategory);
	}

	@Override
	public List<UOM> findUOMsByUOMCatID(long catID) {
		return uomRepo.getUOMsBsdOnUOMCatID(catID);
	}

}
