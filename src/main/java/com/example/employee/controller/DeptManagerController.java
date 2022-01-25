package com.example.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.entity.DeptManager;
import com.example.employee.exception.ApiRequestException;
import com.example.employee.service.DeptManagerService;
import com.example.employee.viewmodel.BaseApiResponse;
import com.example.employee.viewmodel.EmployeeViewModel;
import com.example.employee.viewmodel.MasterViewModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/deptmanager")
@Tag(name = "Dept Manager API", description = "Operations pertaining to Dept Manager")
public class DeptManagerController {
	
	@Autowired
	DeptManagerService service;
	
	@GetMapping
	@Operation(summary = "Get All Dept Manager", description = "Get All Dept Manager", tags = "Dept Manager")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Create Operation", content = @Content(schema = @Schema(implementation = BaseApiResponse.class))) })
	public ResponseEntity<?> findAll() {

		List<DeptManager> result = service.findAll();
		BaseApiResponse errDetail = new BaseApiResponse();
		MasterViewModel mvm = new MasterViewModel();
		errDetail.setStatus(HttpStatus.OK.value());
		errDetail.setMessage(HttpStatus.OK.name());
		errDetail.setDetailmessage("Success get all Dept Manager");
		errDetail.setDetailInfo(result);
		
		mvm.setTotalRows(result.size());
		mvm.setBaseResponse(errDetail);
		
		return new ResponseEntity<MasterViewModel>(mvm, HttpStatus.OK);
	}
	
	
	@PostMapping
	@Operation(summary = "Save New Dept Manager", description = "Save New Dept Manager", tags = "Dept Manager")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Create Operation", content = @Content(schema = @Schema(implementation = BaseApiResponse.class))) })
	public ResponseEntity<BaseApiResponse> insertEmployee(@RequestBody DeptManager vm) throws Exception {

		BaseApiResponse result = new BaseApiResponse();
		if (vm == null) {			
			throw new ApiRequestException("Dept Manager data cannot be null");
		} else {
			result = service.save(vm);
		}

		return new ResponseEntity<BaseApiResponse>(result, HttpStatus.OK);
	}
	
}
