package com.example.employee.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.employee.entity.DeptEmp;
import com.example.employee.entity.DeptManager;
import com.example.employee.entity.Employees;
import com.example.employee.entity.Employees.Gender;
import com.example.employee.exception.ApiRequestException;
import com.example.employee.entity.Salaries;
import com.example.employee.entity.Titles;
import com.example.employee.repository.DeptEmpRepository;
import com.example.employee.repository.DeptManagerRepository;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.SalariesRepository;
import com.example.employee.repository.TitlesRepository;
import com.example.employee.service.EmployeeService;
import com.example.employee.viewmodel.DeptEmpViewModel;
import com.example.employee.viewmodel.DeptManagerViewModel;
import com.example.employee.viewmodel.EmployeeViewModel;
import com.example.employee.viewmodel.BaseApiResponse;
import com.example.employee.viewmodel.SalaryViewModel;
import com.example.employee.viewmodel.TitleViewModel;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	TitlesRepository titleRepo;

	@Autowired
	SalariesRepository salaryRepo;

	@Autowired
	DeptEmpRepository deptEmpRepo;

	@Autowired
	DeptManagerRepository deptManagerRepo;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public List<EmployeeViewModel> getAllEmployees(Optional<Integer> page, Optional<Integer> size,
			Optional<String> orderby) {

		List<EmployeeViewModel> lvm = new ArrayList<EmployeeViewModel>();
		Page<Employees> employee = employeeRepo
				.findAll(PageRequest.of(page.orElse(0), size.orElse(10), Sort.by(orderby.orElse("empNo")).ascending()));

		List<Employees> listOfEmployee = employee.getContent();
		for (int i = 0; i < listOfEmployee.size(); i++) {
			EmployeeViewModel evm = new EmployeeViewModel();
			evm.setEmpNo((listOfEmployee.get(i).getEmpNo().intValue()));
			evm.setBirthDate(dateFormat.format(listOfEmployee.get(i).getBirthDate()));
			evm.setFirstName(listOfEmployee.get(i).getFirstName());
			evm.setLastName(listOfEmployee.get(i).getLastName());
			evm.setHireDate(dateFormat.format(listOfEmployee.get(i).getHireDate()));
			evm.setGender(listOfEmployee.get(i).getGender().name());

			// set salary
			List<SalaryViewModel> listSalaryVm = new ArrayList<SalaryViewModel>();
			List<Salaries> listOfSalary = salaryRepo.findSalariesById(listOfEmployee.get(i).getEmpNo().intValue());
			for (int j = 0; j < listOfSalary.size(); j++) {
				SalaryViewModel svm = new SalaryViewModel();
				svm.setSalary(String.valueOf(listOfSalary.get(j).getSalary()));
				svm.setFromDate(dateFormat.format(listOfSalary.get(j).getFromDate()));
				if (listOfSalary.get(j).getToDate() != null) {
					svm.setToDate(dateFormat.format(listOfSalary.get(j).getToDate()));
				}
				
				listSalaryVm.add(svm);
			}
			evm.setSalary(listSalaryVm);

			// set title
			List<TitleViewModel> listTitleVm = new ArrayList<TitleViewModel>();
			List<Titles> listOfTitle = titleRepo.findTitlesById(listOfEmployee.get(i).getEmpNo().intValue());
			for (int k = 0; k < listOfTitle.size(); k++) {
				TitleViewModel tvm = new TitleViewModel();
				tvm.setFromDate(dateFormat.format(listOfTitle.get(k).getFromDate()));
				if (listOfTitle.get(k).getToDate() != null) {
					tvm.setToDate(dateFormat.format(listOfTitle.get(k).getToDate()));
				}
				
				tvm.setTitle(listOfTitle.get(k).getTitle());
				listTitleVm.add(tvm);
			}
			evm.setTitle(listTitleVm);

			// set dept_emp
			List<DeptEmpViewModel> listDeptEmpVm = new ArrayList<DeptEmpViewModel>();
			List<DeptEmp> listOfDeptEmp = deptEmpRepo.findDeptEmpById(listOfEmployee.get(i).getEmpNo().intValue());
			for (int l = 0; l < listOfDeptEmp.size(); l++) {
				DeptEmpViewModel devm = new DeptEmpViewModel();
				if (listOfDeptEmp.get(l).getToDate() != null) {
					devm.setToDate(dateFormat.format(listOfDeptEmp.get(l).getToDate()));

				}
				devm.setFromDate(dateFormat.format(listOfDeptEmp.get(l).getFromDate()));
				devm.setDeptNo(listOfDeptEmp.get(l).getDeptNo());
				listDeptEmpVm.add(devm);
			}
			evm.setDept_emp(listDeptEmpVm);

			// set dept_manager
			List<DeptManagerViewModel> listDeptManagerVm = new ArrayList<DeptManagerViewModel>();
			List<DeptManager> listOfDeptManager = deptManagerRepo
					.findDeptManagerEmpNo(listOfEmployee.get(i).getEmpNo().intValue());
			for (int m = 0; m < listOfDeptManager.size(); m++) {
				DeptManagerViewModel dmvm = new DeptManagerViewModel();
				dmvm.setDeptNo(listOfDeptManager.get(m).getDeptNo());
				dmvm.setEmpNo(listOfDeptManager.get(m).getEmpNo());
				dmvm.setFromDate(dateFormat.format(listOfDeptManager.get(m).getFromDate()));
				if (listOfDeptManager.get(m).getToDate() != null) {
					dmvm.setToDate(dateFormat.format(listOfDeptManager.get(m).getToDate()));
				}
				
				listDeptManagerVm.add(dmvm);
			}
			evm.setDept_manager(listDeptManagerVm);

			lvm.add(evm);
		}

		return lvm;
	}

	@Override
	public BaseApiResponse saveEmployee(EmployeeViewModel vm) throws Exception {

		BaseApiResponse errDetail = new BaseApiResponse();

		Employees employees = new Employees();
		Salaries salary = new Salaries();
		Titles title = new Titles();
		DeptEmp deptEmp = new DeptEmp();
		DeptManager deptManager = new DeptManager();

		BeanUtils.copyProperties(vm, employees);
		employees.setEmpNo(null);

		Gender gender = null;
		if (vm.getGender().equalsIgnoreCase("M")) {
			gender = gender.M;
		} else {
			gender = gender.F;
		}
		employees.setGender(gender);
		employees.setHireDate(dateFormat.parse(vm.getHireDate()));
		employees.setBirthDate(dateFormat.parse(vm.getBirthDate()));

		BeanUtils.copyProperties(vm.getSalary().get(0), salary);
		salary.setFromDate(dateFormat.parse(vm.getSalary().get(0).getFromDate()));
		if (!StringUtils.isEmpty(vm.getSalary().get(0).getToDate())) {
			salary.setToDate(dateFormat.parse(vm.getSalary().get(0).getToDate()));
		}
		salary.setSalary(Integer.parseInt(vm.getSalary().get(0).getSalary()));

		BeanUtils.copyProperties(vm.getTitle().get(0), title);
		title.setFromDate(dateFormat.parse(vm.getTitle().get(0).getFromDate()));
		if (!StringUtils.isEmpty(vm.getTitle().get(0).getToDate())) {
			title.setToDate(dateFormat.parse(vm.getTitle().get(0).getToDate()));
		}

		BeanUtils.copyProperties(vm.getDept_emp().get(0), deptEmp);
		
		deptEmp.setFromDate(dateFormat.parse(vm.getDept_emp().get(0).getFromDate()));

		if (!StringUtils.isEmpty(vm.getDept_emp().get(0).getToDate())) {
			deptEmp.setToDate(dateFormat.parse(vm.getDept_emp().get(0).getToDate()));
		}

		BeanUtils.copyProperties(vm.getDept_manager().get(0), deptManager);
		if (!isValidDate(vm.getDept_manager().get(0).getFromDate())) {
			throw new ApiRequestException("dept_manager [from_date] is not valid date");
		}
		deptManager.setFromDate(dateFormat.parse(vm.getDept_manager().get(0).getFromDate()));
		deptManager.setEmpNo(vm.getDept_manager().get(0).getEmpNo());

		if (!StringUtils.isEmpty(vm.getDept_manager().get(0).getToDate())) {
			deptManager.setToDate(dateFormat.parse(vm.getDept_manager().get(0).getToDate()));
		}

		// getting the generated id back
		Employees employee = employeeRepo.saveAndFlush(employees);
		salary.setEmpNo(employee.getEmpNo());
		title.setEmpNo(employee.getEmpNo());
		deptEmp.setEmpNo(employee.getEmpNo());
		//deptManager.setEmpNo(employee.getEmpNo());
		salaryRepo.save(salary);
		titleRepo.save(title);
		deptEmpRepo.save(deptEmp);
		//deptManagerRepo.save(deptManager);
		
		// deptManagerRepo.saveDeptManager(deptManager.getDeptNo(), deptManager.getEmpNo(),
				//deptManager.getFromDate(), deptManager.getToDate());

		errDetail.setStatus(HttpStatus.CREATED.value());
		errDetail.setDetailmessage("Employee Created");
		errDetail.setDetailInfo(employee);
		errDetail.setMessage(HttpStatus.CREATED.name());

		return errDetail;

	}

	@Override
	public BaseApiResponse deleteEmployee(int emp_no) {

		BaseApiResponse errDetails = new BaseApiResponse();

		employeeRepo.deleteById(Long.valueOf(emp_no));
		if (!employeeRepo.existsById(Long.valueOf(emp_no))) {
			errDetails.setStatus(HttpStatus.OK.value());
			errDetails.setMessage(HttpStatus.OK.toString());
			errDetails.setDetailmessage("ID " + emp_no + " Deleted");
		}

		return errDetails;
	}

	@Override
	public BaseApiResponse updateEmployee(EmployeeViewModel vm) throws Exception {
		BaseApiResponse errDetail = new BaseApiResponse();

		Employees employees = new Employees();
		Salaries salary = new Salaries();
		Titles title = new Titles();
		DeptEmp deptEmp = new DeptEmp();
		DeptManager deptManager = new DeptManager();

		BeanUtils.copyProperties(vm, employees);

		Gender gender = null;
		if (vm.getGender().equalsIgnoreCase("M")) {
			gender = gender.M;
		} else {
			gender = gender.F;
		}
		employees.setEmpNo(Long.valueOf(vm.getEmpNo()));
		employees.setGender(gender);
		employees.setHireDate(dateFormat.parse(vm.getHireDate()));
		employees.setBirthDate(dateFormat.parse(vm.getBirthDate()));

		if (!StringUtils.isNumeric(vm.getSalary().get(0).getSalary())) {
			throw new ApiRequestException("Salary must be numeric");
		}
		BeanUtils.copyProperties(vm.getSalary().get(0), salary);
		salary.setFromDate(dateFormat.parse(vm.getSalary().get(0).getFromDate()));
		salary.setToDate(dateFormat.parse(vm.getSalary().get(0).getToDate()));
		salary.setSalary(Integer.parseInt(vm.getSalary().get(0).getSalary()));

		BeanUtils.copyProperties(vm.getTitle().get(0), title);
		title.setFromDate(dateFormat.parse(vm.getTitle().get(0).getFromDate()));
		title.setToDate(dateFormat.parse(vm.getTitle().get(0).getToDate()));

		BeanUtils.copyProperties(vm.getDept_emp().get(0), deptEmp);
		if (vm.getDept_emp().get(0).getDeptNo().length() > 4) {
			throw new ApiRequestException("dept_no max 4 character");
		}
		deptEmp.setFromDate(dateFormat.parse(vm.getDept_emp().get(0).getFromDate()));
		deptEmp.setToDate(dateFormat.parse(vm.getDept_emp().get(0).getToDate()));

		BeanUtils.copyProperties(vm.getDept_manager().get(0), deptManager);
		deptManager.setFromDate(dateFormat.parse(vm.getDept_manager().get(0).getFromDate()));
		deptManager.setToDate(dateFormat.parse(vm.getDept_manager().get(0).getToDate()));

		// getting the generated id back
		Employees employee = employeeRepo.saveAndFlush(employees);
		salary.setEmpNo(employee.getEmpNo());
		title.setEmpNo(employee.getEmpNo());
		deptEmp.setEmpNo(employee.getEmpNo());
		deptManager.setEmpNo(employee.getEmpNo());
		salaryRepo.save(salary);
		titleRepo.save(title);
		deptEmpRepo.save(deptEmp);
		//deptManagerRepo.save(deptManager);

		errDetail.setStatus(HttpStatus.OK.value());
		errDetail.setDetailmessage("Employee updated");
		errDetail.setDetailInfo(vm);
		errDetail.setMessage(HttpStatus.OK.name());

		return errDetail;
	}

	

	@Override
	public List<EmployeeViewModel> getEmployeeById(int id) throws Exception {
		
		if (!employeeRepo.existsById(Long.valueOf(id))) {
			throw new ApiRequestException("ID is not Exists");
		}
		
		List<EmployeeViewModel> result = new ArrayList<EmployeeViewModel>();
		EmployeeViewModel employeeVm = new EmployeeViewModel();
		List<SalaryViewModel> listSalaryVm = new ArrayList<SalaryViewModel>();
		Optional<Employees> employee = employeeRepo.findById(Long.valueOf(id));
		List<Salaries> salary = salaryRepo.findSalariesById(id);

		employeeVm.setEmpNo(employee.get().getEmpNo().intValue());
		employeeVm.setBirthDate(dateFormat.format(employee.get().getBirthDate()));
		employeeVm.setFirstName(employee.get().getFirstName());
		employeeVm.setLastName(employee.get().getLastName());
		employeeVm.setGender(employee.get().getGender().name());
		employeeVm.setHireDate(dateFormat.format(employee.get().getHireDate()));

		// set salary
		for (int i = 0; i < salary.size(); i++) {
			SalaryViewModel salaryViewModel = new SalaryViewModel();
			salaryViewModel.setFromDate(dateFormat.format(salary.get(i).getFromDate()));
			if (salary.get(i).getToDate() != null) {
				salaryViewModel.setToDate(dateFormat.format(salary.get(i).getToDate()));
			}
			
			salaryViewModel.setSalary(String.valueOf(salary.get(i).getSalary()));
			listSalaryVm.add(salaryViewModel);
		}
		employeeVm.setSalary(listSalaryVm);

		// set title
		List<TitleViewModel> listTitleVm = new ArrayList<TitleViewModel>();
		List<Titles> listOfTitle = titleRepo.findTitlesById(id);
		for (int k = 0; k < listOfTitle.size(); k++) {
			TitleViewModel tvm = new TitleViewModel();
			tvm.setFromDate(dateFormat.format(listOfTitle.get(k).getFromDate()));
			if (listOfTitle.get(k).getToDate() != null) {
				tvm.setToDate(dateFormat.format(listOfTitle.get(k).getToDate()));
			}
			
			tvm.setTitle(listOfTitle.get(k).getTitle());
			listTitleVm.add(tvm);
		}
		employeeVm.setTitle(listTitleVm);

		// set dept_emp
		List<DeptEmpViewModel> listDeptEmpVm = new ArrayList<DeptEmpViewModel>();
		List<DeptEmp> listOfDeptEmp = deptEmpRepo.findDeptEmpById(id);
		for (int l = 0; l < listOfDeptEmp.size(); l++) {
			DeptEmpViewModel devm = new DeptEmpViewModel();
			if (listOfDeptEmp.get(l).getToDate() != null) {
				devm.setToDate(dateFormat.format(listOfDeptEmp.get(l).getToDate()));
			}
			
			devm.setFromDate(dateFormat.format(listOfDeptEmp.get(l).getFromDate()));
			devm.setDeptNo(listOfDeptEmp.get(l).getDeptNo());
			listDeptEmpVm.add(devm);
		}
		employeeVm.setDept_emp(listDeptEmpVm);

		// set dept_manager
		List<DeptManagerViewModel> listDeptManagerVm = new ArrayList<DeptManagerViewModel>();
		List<DeptManager> listOfDeptManager = deptManagerRepo.findDeptManagerEmpNo(id);
		for (int m = 0; m < listOfDeptManager.size(); m++) {
			DeptManagerViewModel dmvm = new DeptManagerViewModel();
			dmvm.setDeptNo(listOfDeptManager.get(m).getDeptNo());
			dmvm.setEmpNo(listOfDeptManager.get(m).getEmpNo());
			dmvm.setFromDate(dateFormat.format(listOfDeptManager.get(m).getFromDate()));
			if (listOfDeptManager.get(m).getToDate() != null) {
				dmvm.setToDate(dateFormat.format(listOfDeptManager.get(m).getToDate()));
			}
			
			listDeptManagerVm.add(dmvm);
		}
		employeeVm.setDept_manager(listDeptManagerVm);

		result.add(employeeVm);

		return result;
	}

	@Override
	public boolean existsById(int id) {
		return employeeRepo.existsById(Long.valueOf(id));
	}
	
	public boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

}
