package com.inzaa.iot.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inzaa.iot.bean.MasterController;
import com.inzaa.iot.dao.MasterControllerDao;

@Repository("MasterControllerDao")
public class MasterControllerDaoImpl implements MasterControllerDao {
	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public List<MasterController> findAll() {
		return this.mongoTemplate.findAll(MasterController.class);
	}

	@Override
	public MasterController find(String id) {
		return this.mongoTemplate.findById(id, MasterController.class);
	}

	@Override
	public MasterController save(MasterController masterController) {
		this.mongoTemplate.save(masterController);
		return masterController;
	}

	@Override
	public MasterController update(String id, MasterController newMasterController) {
		return this.save(newMasterController);
	}

	@Override
	public void delete(MasterController masterController) {
		if (masterController != null) {
			this.mongoTemplate.remove(masterController);
		}
	}

	@Override
	public void deleteById(String id) {
		this.delete(this.find(id));
	}
}
