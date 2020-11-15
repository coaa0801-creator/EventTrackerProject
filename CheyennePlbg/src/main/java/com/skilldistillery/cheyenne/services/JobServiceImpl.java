package com.skilldistillery.cheyenne.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		List<Job> allJobs = repo.findAll();
		List<Job> newList = new ArrayList<Job>();
		for (Job job : allJobs) {
			int index = 0;
			int active = job.getActive();
			if (active == 1) {
				newList.add(allJobs.get(index));
			}
			index++;

		}
		return newList;
	}

	@Override
	public Job findById(int id) {
		Optional<Job> jobOpt = repo.findById(id);
		Job job = null;
		if (jobOpt.isPresent()) {
			job = jobOpt.get();
			if (job.getActive() == 0) {
				job = null;
			}
		}
		return job;
	}

	@Override
	public Job createJob(Job newJob) {
		return repo.saveAndFlush(newJob);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Job> jobOpt = repo.findById(id);
		if (jobOpt.isPresent()) {
			Job job = jobOpt.get();
			job.setActive(0);
			repo.save(job);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Job updateJob(Job newJob, int id) {
		Optional<Job> jobOpt = repo.findById(id);
		Job update = null;
		if (jobOpt.isPresent()) {
			update = jobOpt.get();
			if (newJob.getName() != null) {update.setName(newJob.getName());	}
			if (newJob.getActive() != 1) {update.setActive(newJob.getActive());	}
			if (newJob.getAddress() != null) {update.setAddress(newJob.getAddress());	}
			if (newJob.getEstimate() > 0) {update.setEstimate(newJob.getEstimate());	}
			if (newJob.getPaid() != 1) {update.setPaid(newJob.getPaid());	}
			if (newJob.getCustomer() != null) {update.setCustomer(newJob.getCustomer());	}
			repo.flush();
		}
		return update;

	}
}
