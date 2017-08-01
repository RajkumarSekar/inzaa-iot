package com.inzaa.iot.mgmt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzaa.iot.bean.MasterController;
import com.inzaa.iot.dao.MasterControllerDao;
import com.inzaa.iot.exceptions.ResourceAlreadyExistsException;
import com.inzaa.iot.exceptions.ResourceNotFoundException;
import com.inzaa.iot.mgmt.interfaces.MasterControllerMgmt;

@Service
public class MasterControllerMgmtImpl implements MasterControllerMgmt {

	@Autowired
	private MasterControllerDao masterControllerDao;

	@Override
	public List<MasterController> findAll() {
		return masterControllerDao.findAll();
	}

	@Override
	public MasterController find(String id) {
		MasterController masterController = masterControllerDao.find(id);
		if (masterController == null) {
			throw new ResourceNotFoundException("MasterController with id " + id + " not found");
		}
		return masterController;
	}

	@Override
	public boolean exists(String id) {
		return masterControllerDao.find(id) != null;
	}

	@Override
	public MasterController save(MasterController masterController) throws ResourceAlreadyExistsException {
		if (this.masterControllerDao.find(masterController.getUuid()) != null) {
			throw new ResourceAlreadyExistsException("MasterController with id " + masterController.getUuid() + " already exists");
		}

		return this.masterControllerDao.save(masterController);
	}

	@Override
	public MasterController update(String id, MasterController newMasterController) {

		if (!id.equals(newMasterController.getUuid()) && this.exists(newMasterController.getUuid())) {
			throw new ResourceAlreadyExistsException(
					"MasterController with id " + newMasterController.getUuid().toString() + " already exists");
		}

		return this.masterControllerDao.update(id, newMasterController);
	}

	@Override
	public void delete(MasterController masterController) {
		this.masterControllerDao.delete(masterController);
	}
}
