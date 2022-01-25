package com.example.employee.viewmodel;

import java.util.List;

public class EmployeeViewModel {
	
	private int empNo;
	private String birthDate;
	private String firstName, lastName, gender;
	private String hireDate;	
	private List<SalaryViewModel> salary;
	private List<TitleViewModel> title;
	private List<DeptEmpViewModel> dept_emp;
	private List<DeptManagerViewModel> dept_manager;
	
	public int getEmpNo() {
		return empNo;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGender() {
		return gender;
	}
	public String getHireDate() {
		return hireDate;
	}
	public List<SalaryViewModel> getSalary() {
		return salary;
	}
	public List<TitleViewModel> getTitle() {
		return title;
	}
	public List<DeptEmpViewModel> getDept_emp() {
		return dept_emp;
	}
	public List<DeptManagerViewModel> getDept_manager() {
		return dept_manager;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public void setSalary(List<SalaryViewModel> salary) {
		this.salary = salary;
	}
	public void setTitle(List<TitleViewModel> title) {
		this.title = title;
	}
	public void setDept_emp(List<DeptEmpViewModel> dept_emp) {
		this.dept_emp = dept_emp;
	}
	public void setDept_manager(List<DeptManagerViewModel> dept_manager) {
		this.dept_manager = dept_manager;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeViewModel [empNo=");
		builder.append(empNo);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", hireDate=");
		builder.append(hireDate);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", title=");
		builder.append(title);
		builder.append(", dept_emp=");
		builder.append(dept_emp);
		builder.append(", dept_manager=");
		builder.append(dept_manager);
		builder.append("]");
		return builder.toString();
	}
	
}
