package com.example.employee.viewmodel;


public class BaseApiResponse {
	private int status;
	private String message;
	private Object detailmessage;
	private int executiontime;
	private Object detailInfo;
	
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorDetails [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", detailmessage=");
		builder.append(detailmessage);
		builder.append(", executiontime=");
		builder.append(executiontime);
		builder.append(", detailInfo=");
		builder.append(detailInfo);
		builder.append("]");
		return builder.toString();
	}	
	
	
}
