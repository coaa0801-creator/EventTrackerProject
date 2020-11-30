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

import com.skilldistillery.cheyenne.entities.Customer;
import com.skilldistillery.cheyenne.services.CustomerService;

@CrossOrigin({"*", "http://localhost:4205"})
@RestController
@RequestMapping("api")
public class CustomerController {
	@Autowired
	private CustomerService customerSvc;
	
	  @GetMapping("customers")
	  public List<Customer> index(){
	    return customerSvc.findAllCustomers();
	  }
	  
	  @GetMapping("customers/{id}")
	  public Customer show(@PathVariable int id, HttpServletResponse response){
		  Customer Customer = customerSvc.findById(id);
		  if (Customer == null) {
			  response.setStatus(404);
		  }
		    return customerSvc.findById(id);
		  }
	  
	  
		@PostMapping("customers")
		public Customer addCustomer(@RequestBody Customer userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				customerSvc.createCustomer(userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/customers/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
		  return userParam;
		}
	
		@PutMapping("customers/{id}")
		public Customer updateCustomer(@PathVariable int id, @RequestBody Customer userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = customerSvc.updateCustomer(userParam, id);
				response.setStatus(201);
				response.setHeader("Location", "api/customers/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		@DeleteMapping("customers/{id}")
		public void deleteCustomer(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = customerSvc.delete(id);
				if(delete) {
				response.setStatus(204);}
				else {
				  response.setStatus(404);
				}
				response.setHeader("Location", "api/customers/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
