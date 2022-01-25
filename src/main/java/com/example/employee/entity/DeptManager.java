package com.example.employee.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dept_manager")
public class DeptManager{
	
	@Id
	private String deptNo;
	private Long empNo;
	private Date fromDate, toDate;
	
	public String getDeptNo() {
		return deptNo;
	}
	public Long getEmpNo() {
		return empNo;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
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
		builder.append("DeptManager [deptNo=");
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
