package com.example.employee.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Departments {
	
	@Id
	private String deptNo; 
	private String deptName;

	public String getDeptNo() {
		return deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Departments [deptNo=");
		builder.append(deptNo);
		builder.append(", deptName=");
		builder.append(deptName);
		builder.append("]");
		return builder.toString();
	}
	
	
}
