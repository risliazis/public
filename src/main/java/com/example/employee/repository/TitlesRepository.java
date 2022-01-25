package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.Titles;

@Repository
public interface TitlesRepository extends JpaRepository<Titles, Long> {

	@Query(value =  "SELECT * FROM TITLES WHERE emp_no = :empNo", nativeQuery = true )
	List<Titles> findTitlesById(@Param("empNo") int empNo);
}
