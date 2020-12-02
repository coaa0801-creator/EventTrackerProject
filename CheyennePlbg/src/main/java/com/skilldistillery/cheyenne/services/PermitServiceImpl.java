package com.skilldistillery.cheyenne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Permit;
import com.skilldistillery.cheyenne.repositories.JobRepository;
import com.skilldistillery.cheyenne.repositories.PermitRepository;

@Service
public class PermitServiceImpl implements PermitService {
	@Autowired
	private PermitRepository pRepo;
	@Autowired
	private JobRepository jRepo;
	
	
	@Override
	public List<Permit> findAllPermits() {
		return pRepo.findAll();
		
	}

	@Override
	public Permit findById(int id) {
		Optional<Permit> permitOpt = pRepo.findById(id);
		Permit permit = null;
		if (permitOpt.isPresent()) {
			permit = permitOpt.get();
		}
		return permit;
	}

	@Override
	public Permit createPermit(Permit newPermit) {
		return pRepo.saveAndFlush(newPermit);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Permit> permitOpt = pRepo.findById(id);
		if (permitOpt.isPresent()) {
			Permit permit = permitOpt.get();
			permit.getJob().removePermit(permit);
			pRepo.delete(permit);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Permit updatePermit(Permit newPermit, int id) {
		Optional<Permit> permitOpt = pRepo.findById(id);
		Permit update = null;
		if (permitOpt.isPresent()) {
			update = permitOpt.get();
			if (newPermit.getIdentifier() != null) {update.setIdentifier(newPermit.getIdentifier());	}
			if (newPermit.getType() != null) {update.setType(newPermit.getType());	}
			if (newPermit.getJob() != null) {jRepo.save(newPermit.getJob());                 update.setJob(newPermit.getJob());	}
			
		}
		return pRepo.saveAndFlush(update);

	}
}
