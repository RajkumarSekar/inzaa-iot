package com.inzaa.iot.dao;

import java.util.List;

import com.inzaa.iot.bean.User;

public interface UserDao {

	List<User> findAll();

	User find(String id);

	User save(User user);

	User update(String id, User newUser);

	void delete(User user);

	void deleteById(String id);
}
