package com.example.employee.viewmodel;

public class DeptManagerViewModel {

	private String deptNo;
	private Long empNo;
	private String fromDate, toDate;
	public String getDeptNo() {
		return deptNo;
	}
	public Long getEmpNo() {
		return empNo;
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
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
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
		builder.append("DeptManagerViewModel [deptNo=");
		builder.append(deptNo);
		builder.append(", empNo=");
		builder.append(empNo);
		builder.append(", fromDate=");
		builder.append(fromDate);
		builder.append(", toDate=");
		builder.append(toDate);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
