package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.Salaries;

@Repository
public interface SalariesRepository extends JpaRepository<Salaries, Long> {

	@Query(value =  "SELECT * FROM SALARIES WHERE emp_no = :empNo", nativeQuery = true )
	List<Salaries> findSalariesById(@Param("empNo") int empNo);
	
}
