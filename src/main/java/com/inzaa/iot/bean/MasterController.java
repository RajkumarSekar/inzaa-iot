package com.inzaa.iot.bean;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "masterController")
public class MasterController extends BaseController {

	private List<ApplianceController> appliancemasterlist;

	public List<ApplianceController> getAppliancemasterlist() {
		return appliancemasterlist;
	}

	public void setAppliancemasterlist(List<ApplianceController> applianceMasterListLocal) {
		appliancemasterlist = applianceMasterListLocal;
	}

}
