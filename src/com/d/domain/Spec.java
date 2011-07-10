package com.d.domain;

import com.d.model.SpecModel;

public class Spec {

	private long id;

	private String name;

	public Spec() {
	}

	public Spec(SpecModel modelTypeModel) {
		this.id = modelTypeModel.getId().getId();
		this.name = modelTypeModel.getName();
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
}
