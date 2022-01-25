package com.example.employee.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "titles")
public class Titles {
	
	@Id
	private Long empNo;
	private String title;
	private Date fromDate, toDate;
	
	public Long getEmpNo() {
		return empNo;
	}
	public String getTitle() {
		return title;
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
	public void setTitle(String title) {
		this.title = title;
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
		builder.append("Titles [empNo=");
		builder.append(empNo);
		builder.append(", title=");
		builder.append(title);
		builder.append(", fromDate=");
		builder.append(fromDate);
		builder.append(", toDate=");
		builder.append(toDate);
		builder.append("]");
		return builder.toString();
	}	
}
