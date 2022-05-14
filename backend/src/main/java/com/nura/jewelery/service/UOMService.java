package com.nura.jewelery.service;

import com.nura.jewelery.entity.uom.UOM;

public interface UOMService {

	public UOM save(UOM uom);

	public UOM findByID(long id);

}
