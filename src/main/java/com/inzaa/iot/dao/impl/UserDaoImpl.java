package com.inzaa.iot.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inzaa.iot.bean.User;
import com.inzaa.iot.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public List<User> findAll() {
		return this.mongoTemplate.findAll(User.class);
	}

	@Override
	public User find(String id) {
		return this.mongoTemplate.findById(id, User.class);
	}

	@Override
	public User save(User user) {
		this.mongoTemplate.save(user);
		return user;
	}

	@Override
	public User update(String id, User newUser) {
		return this.save(newUser);
	}

	@Override
	public void delete(User User) {
		if (User != null) {
			this.mongoTemplate.remove(User);
		}
	}

	@Override
	public void deleteById(String id) {
		this.delete(this.find(id));
	}
}
