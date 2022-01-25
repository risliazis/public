package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import com.example.employee.viewmodel.EmployeeViewModel;
import com.example.employee.viewmodel.BaseApiResponse;

public interface EmployeeService {
	public List<EmployeeViewModel> getAllEmployees(Optional<Integer> page, Optional<Integer> size, Optional<String> orderby);
	public List<EmployeeViewModel> getEmployeeById(int id) throws Exception;
	public BaseApiResponse saveEmployee (EmployeeViewModel vm) throws Exception;
	public BaseApiResponse deleteEmployee (int emp_no);
	public BaseApiResponse updateEmployee (EmployeeViewModel vm) throws Exception;	
	public boolean existsById (int id);
	
}
