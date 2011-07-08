package com.d.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Model {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String name;
	@Persistent
	private Long makerId;

	private String makerName;
	@Persistent
	private Long modelTypeId;
	private String modelTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getModelTypeId() {
		return modelTypeId;
	}

	public void setModelTypeId(Long typeId) {
		this.modelTypeId = typeId;
	}

	public String getModelTypeName() {
		return modelTypeName;
	}

	public void setModelTypeName(String typeName) {
		this.modelTypeName = typeName;
	}

	public String getMakerName() {
		return makerName;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

}
