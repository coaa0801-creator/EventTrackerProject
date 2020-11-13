package com.skilldistillery.cheyenne.services;

import java.util.List;

import com.skilldistillery.cheyenne.entities.Job;

public interface JobService {

	List<Job> findAllJobs();

	Job findById(int id);

	Job createJob(Job newJob);

	boolean delete(int id);

	Job updateJob(Job newJob, int id);
}
