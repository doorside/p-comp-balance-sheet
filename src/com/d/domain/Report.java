package com.d.domain;

import java.util.Date;

import javax.jdo.annotations.Persistent;

import com.d.model.ReportModel;

public class Report {

	private Long id;

	private Date date;

	private Long shopId;

	private String shopName;

	private Long modelId;
	private String modelName;
	/** 投資金額 */
	private Long investment;
	/** 換金金額 */
	private Long realization;

	@Persistent
	private Integer numOfPeople;

	public Report() {
	}

	public Report(ReportModel reportModel) {
		this.id = reportModel.getId().getId();
		this.date = reportModel.getDate();
		this.shopId = reportModel.getShopId();
		this.shopName = reportModel.getShopName();
		this.modelId = reportModel.getModelId();
		this.modelName = reportModel.getModelName();
		this.investment = reportModel.getInvestment();
		this.realization = reportModel.getRealization();
		this.numOfPeople = reportModel.getNumOfPeople();
	}

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
