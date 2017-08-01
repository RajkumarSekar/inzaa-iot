package com.inzaa.iot.bean;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ApplianceController extends BaseController{

	private List<Appliance> appliancelist;

	public List<Appliance> getAppliancelist() {
		return appliancelist;
	}

	public void setApplianceList(List<Appliance> applianceList) {
		this.appliancelist = applianceList;
	}

}
