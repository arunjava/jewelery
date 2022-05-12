package com.nura.jewelery.service;

import java.util.List;
import java.util.Optional;

import com.nura.jewelery.entity.Scheme;


public interface SchemeService {

	public Scheme saveScheme(Scheme scheme);
	
	public Optional<Scheme> getSchemeBsdOnID(long schemeID);
	
	public List<Scheme> getActiveSchemes();
	
}
