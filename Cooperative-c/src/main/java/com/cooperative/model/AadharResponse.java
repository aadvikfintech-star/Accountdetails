package com.cooperative.model;

public class AadharResponse {

	private AadharData data;
	private String status_code;
	private String success;
	private String message;
	private String message_code;

	public AadharData getData() {
		return data;
	}

	public void setData(AadharData data) {
		this.data = data;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage_code() {
		return message_code;
	}

	public void setMessage_code(String message_code) {
		this.message_code = message_code;
	}

}


