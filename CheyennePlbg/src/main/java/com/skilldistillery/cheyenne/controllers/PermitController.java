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

import com.skilldistillery.cheyenne.entities.Permit;
import com.skilldistillery.cheyenne.services.PermitService;

@CrossOrigin({"*", "http://localhost:4205"})
@RestController
@RequestMapping("api")
public class PermitController {
	@Autowired
	private PermitService permitSvc;
	
	  @GetMapping("permits")
	  public List<Permit> index(){
	    return permitSvc.findAllPermits();
	  }
	  
	  @GetMapping("permits/{id}")
	  public Permit show(@PathVariable int id, HttpServletResponse response){
		  Permit permit = permitSvc.findById(id);
		  if (permit == null) {
			  response.setStatus(404);
		  }
		    return permitSvc.findById(id);
		  }
	  
	  
		@PostMapping("permits")
		public Permit addPermit(@RequestBody Permit userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = permitSvc.createPermit(userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/permits/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
		  return userParam;
		}
	
		@PutMapping("permits/{id}")
		public Permit updatePermit(@PathVariable int id, @RequestBody Permit userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = permitSvc.updatePermit(userParam, id);
				response.setStatus(201);
				response.setHeader("Location", "api/permits/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		@DeleteMapping("permits/{id}")
		public void deletePermit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = permitSvc.delete(id);
				if(delete) {
				response.setStatus(204);}
				else {
				  response.setStatus(404);
				}
				response.setHeader("Location", "api/permits/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
