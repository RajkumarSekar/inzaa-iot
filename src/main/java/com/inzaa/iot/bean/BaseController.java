package com.inzaa.iot.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class BaseController {
	@Id
	private String uuid;

	@Field("hotspotConfig")
	private WifiConfig hotspotconfig;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public WifiConfig getHotspotconfig() {
		return hotspotconfig;
	}

	public void setHotspotconfig(WifiConfig hotspotConfig) {
		this.hotspotconfig = hotspotConfig;
	}

}
