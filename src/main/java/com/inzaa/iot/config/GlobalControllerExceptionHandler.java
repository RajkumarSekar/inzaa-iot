package com.inzaa.iot.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inzaa.iot.bean.ErrorResponse;
import com.inzaa.iot.exceptions.ResourceAlreadyExistsException;
import com.inzaa.iot.exceptions.ResourceNotFoundException;

/**
 * Created by fernandosproviero on 5/8/15.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@ExceptionHandler(value = ResourceAlreadyExistsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleResourceAlreadyExists(ResourceAlreadyExistsException e) {
		return new ErrorResponse(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleResourceNotFound(ResourceNotFoundException e) {
		return new ErrorResponse(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleValidationException(MethodArgumentNotValidException e) {
		BindingResult validationResult = e.getBindingResult();
		String object = validationResult.getObjectName();
		ErrorResponse response = new ErrorResponse("RESOURCE_NOT_VALID",
				"Validation error: " + object + " is not valid. ");
		List<FieldError> fieldErrors = validationResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			response.addCause("Invalid " + fieldError.getField(),
					StringUtils.capitalize(fieldError.getField() + " " + fieldError.getDefaultMessage()));
		}
		LOGGER.info("BAD_REQUEST:", e);
		return response;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleException(Exception e) {
		LOGGER.error("Internal error", e);
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.toString());
	}
}
