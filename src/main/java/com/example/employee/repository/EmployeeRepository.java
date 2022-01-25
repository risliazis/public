package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long>{
	
}
