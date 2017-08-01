package com.inzaa.iot.config.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzaa.iot.bean.Appliance;
import com.inzaa.iot.bean.ApplianceController;
import com.inzaa.iot.bean.ApplianceType;
import com.inzaa.iot.bean.MasterController;
import com.inzaa.iot.bean.User;
import com.inzaa.iot.bean.WifiConfig;

public class Main {
	static ObjectMapper m = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {
//		printUser();
		 printMasterController();
	}

	private static void printMasterController() throws JsonProcessingException {
		MasterController controller = new MasterController();
		controller.setUuid("uuid");
		WifiConfig hotspotConfig = new WifiConfig();
		hotspotConfig.setSsid("sankar-wifi");
		hotspotConfig.setAuthenticated(true);
		hotspotConfig.setLocal(false);
		hotspotConfig.setPassword("12345");
		controller.setHotspotconfig(hotspotConfig);
		List<ApplianceController> applianceMasterList = new ArrayList<>();
		ApplianceController applianceController = new ApplianceController();
		List<Appliance> applianceList = new ArrayList<>();
		Appliance appliance = new Appliance();
		appliance.setApplianceType(ApplianceType.tv);
		appliance.setPinIndex(2);
		appliance.setRange(0);
		appliance.setStatus(true);
		applianceList.add(appliance);
		Appliance appliance1 = new Appliance();
		appliance1.setApplianceType(ApplianceType.ac);
		appliance1.setPinIndex(1);
		appliance1.setRange(0);
		appliance1.setStatus(false);
		applianceList.add(appliance1);
		applianceController.setApplianceList(applianceList);
		WifiConfig hotspotConfigLocal = new WifiConfig();
		hotspotConfigLocal.setSsid("sankar-wifi-local");
		hotspotConfigLocal.setAuthenticated(true);
		hotspotConfigLocal.setLocal(true);
		hotspotConfigLocal.setPassword("qwerty@123");
		applianceController.setHotspotconfig(hotspotConfigLocal);
		applianceMasterList.add(applianceController);
		controller.setAppliancemasterlist(applianceMasterList);
		System.out.println(m.writeValueAsString(controller));
	}

	private static void printUser() throws JsonProcessingException {
		User user = new User();
		user.setDob("31/01/1988");
		user.setMails(Collections.singletonList("ksnboopa@gmail.com"));
		user.setMastercontrollerids(Collections.singletonList("id"));
		user.setMobiles(Collections.singletonList("9865016051"));
		user.setName("Sankar");
		user.setSex("m");

		System.out.println(m.writeValueAsString(user));
	}
}
