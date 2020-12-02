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

import com.skilldistillery.cheyenne.entities.Department;
import com.skilldistillery.cheyenne.services.DepartmentService;

@CrossOrigin({"*", "http://localhost:4205"})
@RestController
@RequestMapping("api")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentSvc;
	
	  @GetMapping("departments")
	  public List<Department> index(){
	    return departmentSvc.findAllDepartments();
	  }
	  
	  @GetMapping("departments/{id}")
	  public Department show(@PathVariable int id, HttpServletResponse response){
		  Department department = departmentSvc.findById(id);
		  if (department == null) {
			  response.setStatus(404);
		  }
		    return departmentSvc.findById(id);
		  }
	  
	  
		@PostMapping("departments")
		public Department addDepartment(@RequestBody Department userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = departmentSvc.createDepartment(userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/departments/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
		  return userParam;
		}
	
		@PutMapping("departments/{id}")
		public Department updateDepartment(@PathVariable int id, @RequestBody Department userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = departmentSvc.updateDepartment(userParam, id);
				response.setStatus(201);
				response.setHeader("Location", "api/departments/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		@DeleteMapping("departments/{id}")
		public void deleteDepartment(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = departmentSvc.delete(id);
				if(delete) {
				response.setStatus(204);}
				else {
				  response.setStatus(404);
				}
				response.setHeader("Location", "api/departments/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
