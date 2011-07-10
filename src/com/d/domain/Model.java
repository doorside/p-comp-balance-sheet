package com.d.domain;


public class Model {
	private long id;
	private String name;
	private long makerId;
	private String makerName;
	private long specId;
	private String specName;

	public Model() {
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

	public long getMakerId() {
		return makerId;
	}

	public void setMakerId(long makerId) {
		this.makerId = makerId;
	}

	public long getSpecId() {
		return specId;
	}

	public void setSpecId(long typeId) {
		this.specId = typeId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String typeName) {
		this.specName = typeName;
	}

	public String getMakerName() {
		return makerName;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

}
