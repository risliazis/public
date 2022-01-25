package com.example.employee.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.entity.DeptManager;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManager, String> {

	@Query(value =  "SELECT * FROM dept_manager WHERE emp_no = :empNo", nativeQuery = true )
	List<DeptManager> findDeptManagerEmpNo(@Param("empNo") int empNo);
	
	@Modifying
	@Transactional
	@Query(value =  "INSERT INTO dept_manager VALUES (:deptNo, :empNo, :fromDate, :toDate)", nativeQuery = true) 
	int saveDeptManager(@Param("deptNo") String dept_no, @Param("empNo") Long emp_no, @Param("fromDate") Date from_date, @Param("toDate") @Temporal Date to_date);
}
