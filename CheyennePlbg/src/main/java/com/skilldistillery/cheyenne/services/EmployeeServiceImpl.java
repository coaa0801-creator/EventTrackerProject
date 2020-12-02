package com.skilldistillery.cheyenne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Employee;
import com.skilldistillery.cheyenne.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository repo;

	@Override
	public List<Employee> findAllEmployees() {
		return repo.findAll();
		
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> employeeOpt = repo.findById(id);
		Employee employee = null;
		if (employeeOpt.isPresent()) {
			employee = employeeOpt.get();
		}
		return employee;
	}

	@Override
	public Employee createEmployee(Employee newEmployee) {
		return repo.saveAndFlush(newEmployee);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Employee> employeeOpt = repo.findById(id);
		if (employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			repo.delete(employee);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Employee updateEmployee(Employee newEmployee, int id) {
		Optional<Employee> employeeOpt = repo.findById(id);
		Employee update = null;
		if (employeeOpt.isPresent()) {
			update = employeeOpt.get();
			if (newEmployee.getFirstName() != null) {update.setFirstName(newEmployee.getFirstName());	}
			if (newEmployee.getLastName() != null) {update.setLastName(newEmployee.getLastName());	}
			if (newEmployee.getEmail() != null) {update.setEmail(newEmployee.getEmail());	}
			if (newEmployee.getAddress() != null) {update.setAddress(newEmployee.getAddress());	}
			if (newEmployee.getWage() != null) {update.setWage(newEmployee.getWage());	}
			if (newEmployee.getJobTypes() != null) {update.setJobTypes(newEmployee.getJobTypes());	}
			if (newEmployee.getJobs() != null) {update.setJobs(newEmployee.getJobs());	}
			
		}
		return repo.saveAndFlush(update);

	}
}
