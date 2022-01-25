package com.example.employee.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salaries")
public class Salaries {
	@Id
	private Long empNo;
	private int salary;
	private Date fromDate, toDate;
	public Long getEmpNo() {
		return empNo;
	}
	public int getSalary() {
		return salary;
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
	public void setSalary(int salary) {
		this.salary = salary;
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
		builder.append("Salaries [empNo=");
		builder.append(empNo);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", fromDate=");
		builder.append(fromDate);
		builder.append(", toDate=");
		builder.append(toDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
