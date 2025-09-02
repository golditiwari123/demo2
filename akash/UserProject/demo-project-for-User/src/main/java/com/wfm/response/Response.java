package com.wfm.response;

public class Response {

	private Object data;
	private String message;
	private Boolean result;
	private int status;

	public Response() {
	}

	public Response(Object data, String message, Boolean result, int status) {
		this.data = data;
		this.message = message;
		this.result = result;
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
