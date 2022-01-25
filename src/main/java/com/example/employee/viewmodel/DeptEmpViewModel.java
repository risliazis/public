package com.example.employee.viewmodel;

import java.util.Date;

public class DeptEmpViewModel {
	private String deptNo;
	private String fromDate, toDate;
	public String getDeptNo() {
		return deptNo;
	}
	public String getFromDate() {
		return fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeptEmpViewModel [deptNo=");
		builder.append(deptNo);
		builder.append(", fromDate=");
		builder.append(fromDate);
		builder.append(", toDate=");
		builder.append(toDate);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
