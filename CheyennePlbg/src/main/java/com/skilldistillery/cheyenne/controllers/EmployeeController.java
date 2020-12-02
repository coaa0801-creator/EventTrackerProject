package com.skilldistillery.cheyenne.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.cheyenne.entities.Employee;
import com.skilldistillery.cheyenne.services.EmployeeService;

@CrossOrigin({"*", "http://localhost:4205"})
@RestController
@RequestMapping("api")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeSvc;
	
	  @GetMapping("employees")
	  public List<Employee> index(){
	    return employeeSvc.findAllEmployees();
	  }
	  
	  @GetMapping("employees/{id}")
	  public Employee show(@PathVariable int id, HttpServletResponse response){
		  Employee employee = employeeSvc.findById(id);
		  if (employee == null) {
			  response.setStatus(404);
		  }
		    return employeeSvc.findById(id);
		  }
	  
	  
		@PostMapping("employees")
		public Employee addEmployee(@RequestBody Employee userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = employeeSvc.createEmployee(userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/employees/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
		  return userParam;
		}
	
		@PutMapping("employees/{id}")
		public Employee updateEmployee(@PathVariable int id, @RequestBody Employee userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = employeeSvc.updateEmployee(userParam, id);
				response.setStatus(201);
				response.setHeader("Location", "api/employees/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		@DeleteMapping("employees/{id}")
		public void deleteEmployee(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = employeeSvc.delete(id);
				if(delete) {
				response.setStatus(204);}
				else {
				  response.setStatus(404);
				}
				response.setHeader("Location", "api/employees/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
