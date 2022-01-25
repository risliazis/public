package com.example.employee.exception;

public class ErrorDetail {
	private int status;
	private String message;
	private Object detailmessage;
	private int executiontime;
	private Object detailInfo;
	
	public ErrorDetail(int status, String message, Object detailmessage, int executiontime, Object detailInfo) {
		this.status = status;
		this.message = message;
		this.detailmessage = detailmessage;
		this.executiontime = executiontime;
		this.detailInfo = detailInfo;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getDetailmessage() {
		return detailmessage;
	}

	public int getExecutiontime() {
		return executiontime;
	}

	public Object getDetailInfo() {
		return detailInfo;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetailmessage(Object detailmessage) {
		this.detailmessage = detailmessage;
	}

	public void setExecutiontime(int executiontime) {
		this.executiontime = executiontime;
	}

	public void setDetailInfo(Object detailInfo) {
		this.detailInfo = detailInfo;
	}
	
	
}
