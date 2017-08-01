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

import com.inzaa.iot.bean.MasterController;
import com.inzaa.iot.mgmt.interfaces.MasterControllerMgmt;

@Controller
@RequestMapping(value = "api/master-controller")
public class MasterControllerRestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MasterControllerRestService.class);

	@Autowired
	private MasterControllerMgmt masterControllerMgmt;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<MasterController> getMasterControllers() throws InterruptedException {
		LOGGER.info("/masterControllers [GET]");
		return masterControllerMgmt.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public MasterController getMasterControllersById(@PathVariable(value = "id") String id) {
		LOGGER.info("/masterControllers/{} [GET]", id);
		return masterControllerMgmt.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public MasterController createMasterController(@RequestBody @Valid MasterController masterController) {
		LOGGER.info("/masterControllers [POST]: {}", masterController);

		return masterControllerMgmt.save(masterController);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public MasterController updateMasterController(@PathVariable(value = "id") String id, @RequestBody @Valid MasterController masterController) {
		LOGGER.info("/masterControllers/{} [PUT] {}", id, masterController);

		return masterControllerMgmt.update(id, masterController);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteMasterControllersById(@PathVariable(value = "id") String id) {
		LOGGER.info("/masterControllers/{} [DELETE]", id);
		MasterController masterController = masterControllerMgmt.find(id);
		masterControllerMgmt.delete(masterController);
	}

}
