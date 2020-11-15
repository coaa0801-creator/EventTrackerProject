package com.skilldistillery.cheyenne.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.cheyenne.entities.Job;
import com.skilldistillery.cheyenne.services.JobService;

@RestController
@RequestMapping("api")
public class JobController {
	@Autowired
	private JobService jobSvc;
	
	  @GetMapping("jobs")
	  public List<Job> index(){
	    return jobSvc.findAllJobs();
	  }
	  
	  @GetMapping("jobs/{id}")
	  public Job show(@PathVariable int id){
		    return jobSvc.findById(id);
		  }
	  
	  
		@PostMapping("jobs")
		public Job addJob(@RequestBody Job userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				jobSvc.createJob(userParam);
				response.setStatus(201);
				response.setHeader("Location", "api/jobs/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
		  return userParam;
		}
	
		@PutMapping("jobs/{id}")
		public Job updateJob(@PathVariable int id, @RequestBody Job userParam, HttpServletRequest request, HttpServletResponse response){
			
			
			try {
				userParam = jobSvc.updateJob(userParam, id);
				response.setStatus(201);
				response.setHeader("Location", "api/jobs/" + userParam.getId());
			} catch (Exception e) {
				response.setStatus(400);
			}
			return userParam;
		}
		@DeleteMapping("jobs/{id}")
		public void deleteJob(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
			try {
				boolean delete = jobSvc.delete(id);
				if(delete) {
				response.setStatus(204);}
				else {
				  response.setStatus(404);
				}
				response.setHeader("Location", "api/Jobs/");
			} catch (Exception e) {
				response.setStatus(400);
			}
			
		}
	
}
