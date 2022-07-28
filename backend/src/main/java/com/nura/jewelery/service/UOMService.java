package com.nura.jewelery.service;

import java.util.List;

import com.nura.jewelery.entity.uom.UOM;
import com.nura.jewelery.entity.uom.UOMCategory;

public interface UOMService {

	public UOM save(UOM uom);

	public UOM findByID(long id);

	public UOMCategory findByUOMCatID(long id);

	public List<UOM> findUOMsByUOMCatID(long catID);

	public UOMCategory saveUOMCategory(UOMCategory uomCategory);

}
