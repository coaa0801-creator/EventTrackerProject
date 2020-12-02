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

import com.skilldistillery.cheyenne.entities.Part;
import com.skilldistillery.cheyenne.services.PartService;

@CrossOrigin({"*", "http://localhost:4205"})
@RestController
@RequestMapping("api")
public class PartController {
	@Autowired
	private PartService partSvc;
	
	  @GetMapping("parts")
	  public List<Part> index(){
	    return partSvc.findAllParts();
	  }
	  
	  @GetMapping("parts/{id}")
	  public Part show(@PathVariable int id, HttpServletResponse response){
		  Part part = partSvc.findById(id);
		  if (part == null) {
			  response.setStatus(404);
		  }
		    return partSvc.findById(id);
		  }
	  
	  
		@PostMapping("parts")
		public Part addPart(@RequestBody Part userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = partSvc.createPart(userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/parts/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
		  return userParam;
		}
	
		@PutMapping("parts/{id}")
		public Part updatePart(@PathVariable int id, @RequestBody Part userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = partSvc.updatePart(userParam, id);
				response.setStatus(201);
				response.setHeader("Location", "api/parts/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		@DeleteMapping("parts/{id}")
		public void deletePart(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = partSvc.delete(id);
				if(delete) {
				response.setStatus(204);}
				else {
				  response.setStatus(404);
				}
				response.setHeader("Location", "api/parts/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
