package com.inzaa.iot.mgmt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzaa.iot.bean.User;
import com.inzaa.iot.dao.UserDao;
import com.inzaa.iot.exceptions.ResourceAlreadyExistsException;
import com.inzaa.iot.exceptions.ResourceNotFoundException;
import com.inzaa.iot.mgmt.interfaces.UserMgmt;

@Service
public class UserMgmtImpl implements UserMgmt {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User find(String id) {
		User user = userDao.find(id);
		if (user == null) {
			throw new ResourceNotFoundException("User with id " + id + " not found");
		}
		return user;
	}
	
	@Override
	public boolean exists(String id) {
		return userDao.find(id) != null;
	}

	@Override
	public User save(User user) throws ResourceAlreadyExistsException {
		if (this.userDao.find(user.getId()) != null) {
			throw new ResourceAlreadyExistsException("User with id " + user.getId() + " already exists");
		}
		
		return this.userDao.save(user);
	}

	@Override
	public User update(String id, User newUser) {
    	
    	if (!id.equals(newUser.getId()) && this.exists(newUser.getId())) {
    		throw new ResourceAlreadyExistsException("User with id " + newUser.getId().toString() + " already exists");
    	}
		
		return this.userDao.update(id, newUser);
	}

	@Override
	public void delete(User user) {
		this.userDao.delete(user);
	}
}
