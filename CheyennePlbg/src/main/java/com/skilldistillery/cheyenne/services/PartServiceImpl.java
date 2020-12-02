package com.skilldistillery.cheyenne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Part;
import com.skilldistillery.cheyenne.repositories.DepartmentRepository;
import com.skilldistillery.cheyenne.repositories.JobRepository;
import com.skilldistillery.cheyenne.repositories.PartRepository;

@Service
public class PartServiceImpl implements PartService {
	@Autowired
	private PartRepository pRepo;
	@Autowired
	private JobRepository jRepo;
	@Autowired
	private DepartmentRepository dRepo;

	@Override
	public List<Part> findAllParts() {
		return pRepo.findAll();
		
	}

	@Override
	public Part findById(int id) {
		Optional<Part> partOpt = pRepo.findById(id);
		Part part = null;
		if (partOpt.isPresent()) {
			part = partOpt.get();
		}
		return part;
	}

	@Override
	public Part createPart(Part newPart) {
		return pRepo.saveAndFlush(newPart);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Part> partOpt = pRepo.findById(id);
		if (partOpt.isPresent()) {
			Part part = partOpt.get();
			pRepo.delete(part);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Part updatePart(Part newPart, int id) {
		Optional<Part> partOpt = pRepo.findById(id);
		Part update = null;
		if (partOpt.isPresent()) {
			update = partOpt.get();
			if (newPart.getPrice() != null) {update.setPrice(newPart.getPrice());	}
			if (newPart.getName() != null) {update.setName(newPart.getName());	}
			if (newPart.getJob() != null) {jRepo.save(newPart.getJob());                 update.setJob(newPart.getJob());	}
			if (newPart.getCondition() != null) {update.setCondition(newPart.getCondition());	}
			if (newPart.getShipTime() != null) {update.setShipTime(newPart.getShipTime());	}
			if (newPart.getJobType() != null) {dRepo.save(newPart.getJobType());        update.setJobType(newPart.getJobType());	}
			if (newPart.getAvailable() != update.getAvailable()) {update.setAvailable(newPart.getAvailable());	}
			
		}
		return pRepo.saveAndFlush(update);

	}
}
