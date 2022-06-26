package com.nura.jewelery.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nura.jewelery.entity.Scheme;
import com.nura.jewelery.repository.SchemeRepository;
import com.nura.jewelery.service.SchemeService;

@Service
@Transactional
public class SchemeServiceImpl implements SchemeService {

	@Autowired
	private SchemeRepository schemeRepository;

	@Override
	public Scheme saveScheme(Scheme scheme) {
		return schemeRepository.save(scheme);
	}

	@Override
	public Optional<Scheme> getSchemeBsdOnID(long schemeID) {
		return schemeRepository.findById(schemeID);
	}

	@Override
	public List<Scheme> getActiveSchemes() {
		return schemeRepository.activeSchemes();
	}

	@Override
	public List<Scheme> getActiveSchemesForCustomerID(long custID) {
		return schemeRepository.getAllSchemsForCustID(custID);
	}

	@Override
	public List<Scheme> getAllSchemesForCustomerID(long custID) {
		return schemeRepository.getAllSchemsForCustID(custID);
	}

}
