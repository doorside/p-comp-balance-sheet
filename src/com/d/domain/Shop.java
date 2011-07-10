package com.d.domain;

import com.d.model.ShopModel;

public class Shop {
	private long id;
	private String name;
	private long conversionRatio;

	public Shop() {
	}

	public Shop(ShopModel shopModel) {
		this.id = shopModel.getId().getId();
		this.name = shopModel.getName();
		this.conversionRatio = shopModel.getConversionRatio();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getConversionRatio() {
		return conversionRatio;
	}

	public void setConversionRatio(long conversionRatio) {
		this.conversionRatio = conversionRatio;
	}
}
