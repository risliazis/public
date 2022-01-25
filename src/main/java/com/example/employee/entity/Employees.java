package com.example.employee.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.example.employee.viewmodel.PostgreSQLEnumType;

@Entity
@Table(name = "employees")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)
public class Employees {
	public static enum Gender {M, F};
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "emp_no")
	private Long empNo;
	private Date birthDate;
	private String firstName, lastName;
	@Enumerated(EnumType.STRING)
    @Column(columnDefinition = "gender")
    @Type( type = "pgsql_enum" )
	private Gender gender;
	private Date hireDate;
	
	
	public Long getEmpNo() {
		return empNo;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employees [empNo=");
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
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
