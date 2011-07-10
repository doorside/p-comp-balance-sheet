package com.d.model;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model(kind = "Shop")
public class ShopModel {
	@Attribute(primaryKey = true)
	private Key id;
	private String name;
	private Long conversionRatio;

	public ShopModel() {
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getConversionRatio() {
		return conversionRatio;
	}

	public void setConversionRatio(Long conversionRatio) {
		this.conversionRatio = conversionRatio;
	}
}
