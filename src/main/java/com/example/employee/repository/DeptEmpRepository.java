package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.DeptEmp;

@Repository
public interface DeptEmpRepository extends JpaRepository<DeptEmp, Integer>{

	@Query(value =  "SELECT * FROM dept_emp WHERE emp_no = :empNo", nativeQuery = true )
	List<DeptEmp> findDeptEmpById(@Param("empNo") int empNo);
}
