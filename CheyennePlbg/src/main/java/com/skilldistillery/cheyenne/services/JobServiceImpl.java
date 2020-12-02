package com.skilldistillery.cheyenne.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Address;
import com.skilldistillery.cheyenne.entities.Job;
import com.skilldistillery.cheyenne.repositories.AddressRepository;
import com.skilldistillery.cheyenne.repositories.CustomerRepository;
import com.skilldistillery.cheyenne.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jRepo;
	@Autowired
	private CustomerRepository cRepo;
	@Autowired
	private AddressRepository aRepo;

	@Override
	public List<Job> findAllJobs() {
		List<Job> allJobs = jRepo.findAll();
		List<Job> newList = new ArrayList<Job>();
		int index = 0;
		for (Job job : allJobs) {
			if (job.getActive()) {
				newList.add(allJobs.get(index));
			}
			index++;

		}
		return newList;
	}

	@Override
	public Job findById(int id) {
		Optional<Job> jobOpt = jRepo.findById(id);
		Job job = null;
		if (jobOpt.isPresent()) {
			job = jobOpt.get();
			if (job.getActive() == false) {
				job = null;
			}
		}
		return job;
	}

	@Override
	public Job createJob(Job newJob) {
		if(newJob.getAddress() != null){
			aRepo.saveAndFlush(newJob.getAddress());
		}
		if(newJob.getCustomer() != null) {
				cRepo.saveAndFlush(newJob.getCustomer());
			}
//		newJob.getAddress().setCustomer(newJob.getCustomer());
//		if(!newJob.getCustomer().getAddresses().contains(newJob.getAddress())) {
//			newJob.getCustomer().addAddress(newJob.getAddress());
//		}
		cRepo.save(newJob.getCustomer());
		aRepo.save(newJob.getAddress());
		
		return jRepo.saveAndFlush(newJob);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Job> jobOpt = jRepo.findById(id);
		if (jobOpt.isPresent()) {
			Job job = jobOpt.get();
			job.setActive(false);
			jRepo.save(job);
			deleted = true;
			return deleted;
		}
		return deleted;
	}

	@Override
	public Job updateJob(Job newJob, int id) {
		Optional<Job> jobOpt = jRepo.findById(id);
		Job update = null;
		if (jobOpt.isPresent()) {
			update = jobOpt.get();
			if (newJob.getCustomer() != null) {
				for (Address a : newJob.getCustomer().getAddresses()) {
					aRepo.saveAndFlush(a);
					
				}
				cRepo.saveAndFlush(newJob.getCustomer());update.setCustomer(newJob.getCustomer());	}
			if (newJob.getName() != null) {update.setName(newJob.getName());	}
			update.setActive(newJob.getActive());	
			if (newJob.getAddress() != null) {aRepo.saveAndFlush(newJob.getAddress());update.setAddress(newJob.getAddress());	}
			if (newJob.getEstimate() > 0) {update.setEstimate(newJob.getEstimate());	}
			if (newJob.getPaid() != newJob.getPaid()) {update.setPaid(newJob.getPaid());	}
			
		}
		return jRepo.saveAndFlush(update);

	}
}
