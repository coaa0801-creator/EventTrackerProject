package com.skilldistillery.cheyenne.services;

import java.util.List;

import com.skilldistillery.cheyenne.entities.Employee;

public interface EmployeeService {

	List<Employee> findAllEmployees();

	Employee findById(int id);

	Employee createEmployee(Employee newEmployee);

	boolean delete(int id);

	Employee updateEmployee(Employee newEmployee, int id);
}
