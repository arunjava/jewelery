package com.nura.jewelery.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.exception.NotFoundException;
import com.nura.jewelery.repository.UOMRepository;
import com.nura.jewelery.service.UOMService;

@Service
@Transactional
public class UOMServiceImpl implements UOMService {

	@Autowired
	private UOMRepository uomRepo;

	@Override
	public UOM save(UOM uom) {
		return uomRepo.save(uom);
	}

	@Override
	public UOM findByID(long id) {
		Optional<UOM> uom = uomRepo.findById(id);
		if (uom.isEmpty()) {
			throw new NotFoundException("UOM not found");
		}
		return uom.get();
	}

}
