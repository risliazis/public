package com.example.employee.service;

import java.util.List;

import com.example.employee.entity.DeptManager;
import com.example.employee.viewmodel.BaseApiResponse;

public interface DeptManagerService {
	public List<DeptManager> findAll();
	public BaseApiResponse save(DeptManager deptManager) throws Exception;

}
