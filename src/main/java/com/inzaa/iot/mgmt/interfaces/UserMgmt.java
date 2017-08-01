package com.inzaa.iot.mgmt.interfaces;

import java.util.List;

import com.inzaa.iot.bean.User;
import com.inzaa.iot.exceptions.ResourceAlreadyExistsException;

public interface UserMgmt {
	List<User> findAll();

	User find(String id);

	boolean exists(String id);

	User save(User user) throws ResourceAlreadyExistsException;

	User update(String id, User newUser);

	void delete(User user);
}
