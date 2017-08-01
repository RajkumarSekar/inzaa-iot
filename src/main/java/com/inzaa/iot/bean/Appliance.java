package com.inzaa.iot.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Appliance {

	private ApplianceType applianceType;

	private boolean status;

	private Integer range;

	private int pinIndex;

	public ApplianceType getApplianceType() {
		return applianceType;
	}

	public void setApplianceType(ApplianceType applianceTypeLocal) {
		applianceType = applianceTypeLocal;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public int getPinIndex() {
		return pinIndex;
	}

	public void setPinIndex(int pinIndex) {
		this.pinIndex = pinIndex;
	}

}
