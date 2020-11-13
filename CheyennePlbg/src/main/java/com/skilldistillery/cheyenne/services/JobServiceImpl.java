package com.skilldistillery.cheyenne.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Job;
import com.skilldistillery.cheyenne.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository repo;

	@Override
	public List<Job> findAllJobs() {
		return repo.findAll();
	}

	@Override
	public Job findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job createJob(Job newJob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Job updateJob(Job newJob, int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
