package com.example.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.exception.ApiRequestException;
import com.example.employee.service.EmployeeService;
import com.example.employee.viewmodel.EmployeeViewModel;
import com.example.employee.viewmodel.MasterViewModel;
import com.example.employee.viewmodel.BaseApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/employee")
@Tag(name = "Employee API", description = "Operations pertaining to Employee")

public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping
	@Operation(summary = "Get All Employee", description = "Get All Employee", tags = "Employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Create Operation", content = @Content(schema = @Schema(implementation = BaseApiResponse.class))) })
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") Optional<Integer> page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Optional<Integer> size,
			@RequestParam(value = "orderby", required = false) Optional<String> orderby) {

		List<EmployeeViewModel> result = service.getAllEmployees(page, size, orderby);
		BaseApiResponse errDetail = new BaseApiResponse();
		MasterViewModel mvm = new MasterViewModel();
		errDetail.setStatus(HttpStatus.OK.value());
		errDetail.setMessage(HttpStatus.OK.name());
		errDetail.setDetailmessage("Success get all employee");
		errDetail.setDetailInfo(result);
		
		mvm.setTotalRows(result.size());
		mvm.setBaseResponse(errDetail);
		
		return new ResponseEntity<MasterViewModel>(mvm, HttpStatus.OK);
	}

	@GetMapping("/{empNo}")
	@Operation(summary = "Get Employee By Emp No", description = "Get Employee By Emp No", tags = "Employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Create Operation", content = @Content(schema = @Schema(implementation = BaseApiResponse.class))) })
	public ResponseEntity<?> findByEmpNo(@PathVariable("empNo") int empNo) throws Exception {
		BaseApiResponse result = new BaseApiResponse();
		List<EmployeeViewModel> lvm = new ArrayList<EmployeeViewModel>();
		EmployeeViewModel vm = new EmployeeViewModel();
		if (empNo == 0) {
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			result.setMessage(HttpStatus.BAD_REQUEST.toString());
			result.setDetailmessage("Employee data cannot be null");
			result.setDetailInfo(null);
		}

		lvm = service.getEmployeeById(empNo);
		

		if (lvm.size() < 1) {
			result.setStatus(HttpStatus.OK.value());
			result.setMessage("Success");
			result.setDetailmessage("Employee data not found");
			result.setDetailInfo(null);
		} else {
			result.setStatus(HttpStatus.OK.value());
			result.setMessage("Success");
			result.setDetailmessage("Employee data found");
			result.setDetailInfo(lvm);
		}

		 
		EntityModel<BaseApiResponse> resource = EntityModel.of(result);
		resource.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateByEmpNo(new EmployeeViewModel()))
				.withRel("update-employee"));
		resource.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteByEmpNo(empNo))
				.withRel("delete-employee"));

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Save New Employee", description = "Save New Employee", tags = "Employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Create Operation", content = @Content(schema = @Schema(implementation = BaseApiResponse.class))) })
	public ResponseEntity<BaseApiResponse> insertEmployee(@RequestBody EmployeeViewModel vm) throws Exception {

		BaseApiResponse result = new BaseApiResponse();
		if (vm == null) {
			result.setStatus(400);
			result.setMessage(HttpStatus.BAD_REQUEST.toString());
			result.setDetailmessage("Employee data cannot be null");
			result.setDetailInfo(null);
		} else {
			result = service.saveEmployee(vm);

		}

		return new ResponseEntity<BaseApiResponse>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{empNo}")
	@Operation(summary = "Delete Employee By Emp No", description = "Delete Employee By Emp No", tags = "Employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Create Operation", content = @Content(schema = @Schema(implementation = BaseApiResponse.class))) })
	public ResponseEntity<BaseApiResponse> deleteByEmpNo(@PathVariable("empNo") int empNo) {
		BaseApiResponse errDetails = new BaseApiResponse();

		if (empNo == 0) {
			throw new ApiRequestException("emp No cannot be null");
		}

		if (!service.existsById(empNo)) {
			throw new ApiRequestException("emp_No not found");
		} else {
			errDetails = service.deleteEmployee(empNo);
		}

		return new ResponseEntity<BaseApiResponse>(errDetails, HttpStatus.OK);
	}

	@PutMapping
	
	@Operation(summary = "Update Employee By Emp No", description = "Update Employee By Emp No", tags = "Employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful Create Operation", content = @Content(schema = @Schema(implementation = BaseApiResponse.class))) })
	public ResponseEntity<?> updateByEmpNo(@RequestBody EmployeeViewModel vm) throws Exception {
		BaseApiResponse errDetail = new BaseApiResponse();

		if (service.existsById(vm.getEmpNo())) {
			errDetail = service.updateEmployee(vm);
		} else {
			throw new ApiRequestException("empNo not found");
		}
		
		EntityModel<BaseApiResponse> resource = EntityModel.of(errDetail);
		resource.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateByEmpNo(new EmployeeViewModel()))
				.withRel("update-employee"));
		resource.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteByEmpNo(vm.getEmpNo()))
				.withRel("delete-employee"));

		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

}
