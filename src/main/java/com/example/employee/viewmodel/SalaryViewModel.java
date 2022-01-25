package com.example.employee.viewmodel;

import java.util.Date;

public class SalaryViewModel {
	public String salary;
	public String fromDate, toDate;
	public String getSalary() {
		return salary;
	}
	public String getFromDate() {
		return fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setSalary(String salary) {
		this.salary = salary;
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
		builder.append("SalaryViewModel [salary=");
		builder.append(salary);
		builder.append(", fromDate=");
		builder.append(fromDate);
		builder.append(", toDate=");
		builder.append(toDate);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
