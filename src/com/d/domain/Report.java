package com.d.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Report {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private Date date;
	@Persistent
	private Long shopId;

	private String shopName;
	@Persistent
	private Long modelId;
	private String modelName;

	/** 投資金額 */
	@Persistent
	private Long investment;
	/** 換金金額 */
	@Persistent
	private Long realization;

	@Persistent
	private Integer numOfPeople;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getInvestment() {
		return investment;
	}

	public void setInvestment(Long investment) {
		this.investment = investment;
	}

	public Long getRealization() {
		return realization;
	}

	public void setRealization(Long realization) {
		this.realization = realization;
	}

	public Integer getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(Integer numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
}
