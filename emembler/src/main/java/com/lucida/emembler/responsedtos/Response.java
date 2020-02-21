package com.lucida.emembler.responsedtos;

import java.util.List;
import java.util.Map;

/**
 * Response Object
 * 
 * @author Ravindra
 *
 */
public class Response {

	private String responseStatus;
	private String errorCode;
	private String errorDescrition;
	private String successDescription;
	private List<String> errors;

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getSuccessDescription() {
		return successDescription;
	}

	public void setSuccessDescription(String successDescription) {
		this.successDescription = successDescription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescrition() {
		return errorDescrition;
	}

	public void setErrorDescrition(String errorDescrition) {
		this.errorDescrition = errorDescrition;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
