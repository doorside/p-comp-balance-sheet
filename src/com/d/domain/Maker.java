package com.d.domain;

import com.d.model.MakerModel;

public class Maker {

	private long id = -1;

	private String name = "";

	public Maker() {
	}

	public Maker(MakerModel makerModel) {
		id = makerModel.getId().getId();
		name = makerModel.getName();
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
