package com.example.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.employee.entity.DeptManager;
import com.example.employee.exception.ApiRequestException;
import com.example.employee.repository.DepartmentsRepository;
import com.example.employee.repository.DeptManagerRepository;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.DeptManagerService;
import com.example.employee.viewmodel.BaseApiResponse;

@Service
public class DeptManagerServiceImpl implements DeptManagerService{
	
	@Autowired
	DeptManagerRepository deptManagerRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	DepartmentsRepository departmentRepo;
	

	@Override
	public List<DeptManager> findAll() {
		
		return deptManagerRepo.findAll();
	}

	@Override
	public BaseApiResponse save(DeptManager entity) throws Exception{
		
		
		if (!employeeRepo.existsById(entity.getEmpNo())) {
			throw new ApiRequestException("EMP NO must be available in employee");
		}
		
		if (!deptManagerRepo.existsById(entity.getDeptNo())) {
			throw new ApiRequestException("dept no must be available in departments");
		}
		
		BaseApiResponse result = new BaseApiResponse();		
		DeptManager deptManager = deptManagerRepo.saveAndFlush(entity);
		
		result.setStatus(HttpStatus.CREATED.value());
		result.setDetailmessage("Dept Manager Created");
		result.setDetailInfo(deptManager);
		result.setMessage(HttpStatus.CREATED.name());
		
		
		return result;
		
	}

}
