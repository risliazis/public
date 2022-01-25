package com.example.employee.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dept_emp")
public class DeptEmp {
	
	@Id
	private Long empNo;
	
	private String deptNo;
	
	private Date fromDate, toDate;
	public Long getEmpNo() {
		return empNo;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeptEmp [empNo=");
		builder.append(empNo);
		builder.append(", deptNo=");
		builder.append(deptNo);
		builder.append(", fromDate=");
		builder.append(fromDate);
		builder.append(", toDate=");
		builder.append(toDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
