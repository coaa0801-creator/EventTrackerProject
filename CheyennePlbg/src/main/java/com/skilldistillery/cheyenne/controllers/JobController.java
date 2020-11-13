package com.skilldistillery.cheyenne.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
