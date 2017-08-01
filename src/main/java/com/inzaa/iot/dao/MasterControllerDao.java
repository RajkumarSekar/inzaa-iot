package com.inzaa.iot.dao;

import java.util.List;

import com.inzaa.iot.bean.MasterController;

public interface MasterControllerDao {

	List<MasterController> findAll();

	MasterController find(String id);

	MasterController save(MasterController masterController);

	MasterController update(String id, MasterController newMasterController);

	void delete(MasterController masterController);

	void deleteById(String id);
}
