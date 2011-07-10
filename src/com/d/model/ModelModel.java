package com.d.model;

import org.slim3.datastore.Attribute;

import com.google.appengine.api.datastore.Key;

@org.slim3.datastore.Model(kind = "Model")
public class ModelModel {
	@Attribute(primaryKey = true)
	private Key id;
	private String name;
	private Long makerId;
	private Long specId;

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

	public Long getMakerId() {
		return makerId;
	}

	public void setMakerId(Long makerId) {
		this.makerId = makerId;
	}

	public Long getSpecId() {
		return specId;
	}

	public void setSpecId(Long typeId) {
		this.specId = typeId;
	}

}
