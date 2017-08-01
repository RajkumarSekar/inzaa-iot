package com.inzaa.iot.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inzaa.iot.bean.User;
import com.inzaa.iot.mgmt.interfaces.UserMgmt;

@Controller
@RequestMapping(value = "api/users")
public class UserRestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestService.class);

	@Autowired
	private UserMgmt userMgmt;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUsers() throws InterruptedException {
		LOGGER.info("/users [GET]");
		return userMgmt.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUsersById(@PathVariable(value = "id") String id) {
		LOGGER.info("/users/{} [GET]", id);
		return userMgmt.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public User createUser(@RequestBody @Valid User user) {
		LOGGER.info("/users [POST]: {}", user);

		return userMgmt.save(user);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public User updateUser(@PathVariable(value = "id") String id, @RequestBody @Valid User user) {
		LOGGER.info("/users/{} [PUT] {}", id, user);

		return userMgmt.update(id, user);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteUsersById(@PathVariable(value = "id") String id) {
		LOGGER.info("/users/{} [DELETE]", id);
		User user = userMgmt.find(id);
		userMgmt.delete(user);
	}

}
