package com.inzaa.iot.mgmt.interfaces;

import java.util.List;

import com.inzaa.iot.bean.MasterController;
import com.inzaa.iot.exceptions.ResourceAlreadyExistsException;

public interface MasterControllerMgmt {

	List<MasterController> findAll();

	MasterController find(String id);

	boolean exists(String id);

	MasterController save(MasterController masterController) throws ResourceAlreadyExistsException;

	MasterController update(String id, MasterController newMasterController);

	void delete(MasterController masterController);
}
