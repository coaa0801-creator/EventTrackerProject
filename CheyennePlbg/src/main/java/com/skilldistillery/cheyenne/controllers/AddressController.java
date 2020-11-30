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

import com.skilldistillery.cheyenne.entities.Address;
import com.skilldistillery.cheyenne.services.AddressService;

@CrossOrigin({"*", "http://localhost:4205"})
@RestController
@RequestMapping("api")
public class AddressController {
	@Autowired
	private AddressService AddressSvc;
	
	  @GetMapping("addresses")
	  public List<Address> index(){
	    return AddressSvc.findAllAddresss();
	  }
	  
	  @GetMapping("addresses/{id}")
	  public Address show(@PathVariable int id, HttpServletResponse response){
		  Address Address = AddressSvc.findById(id);
		  if (Address == null) {
			  response.setStatus(404);
		  }
		    return AddressSvc.findById(id);
		  }
	  
	  
		@PostMapping("addresses")
		public Address addAddress(@RequestBody Address userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				AddressSvc.createAddress(userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/Addresss/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
		  return userParam;
		}
	
		@PutMapping("addresses/{id}")
		public Address updateAddress(@PathVariable int id, @RequestBody Address userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = AddressSvc.updateAddress(userParam, id);
				response.setStatus(201);
				response.setHeader("Location", "api/addresses/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		@DeleteMapping("addresses/{id}")
		public void deleteAddress(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = AddressSvc.delete(id);
				if(delete) {
				response.setStatus(204);}
				else {
				  response.setStatus(404);
				}
				response.setHeader("Location", "api/addresses/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
