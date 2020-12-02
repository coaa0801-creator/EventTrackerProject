package com.skilldistillery.cheyenne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Department;
import com.skilldistillery.cheyenne.entities.Employee;
import com.skilldistillery.cheyenne.entities.Job;
import com.skilldistillery.cheyenne.entities.Part;
import com.skilldistillery.cheyenne.repositories.DepartmentRepository;
import com.skilldistillery.cheyenne.repositories.EmployeeRepository;
import com.skilldistillery.cheyenne.repositories.JobRepository;
import com.skilldistillery.cheyenne.repositories.PartRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository repo;
	@Autowired
	private JobRepository jRepo;
	@Autowired
	private PartRepository pRepo;
	@Autowired
	private EmployeeRepository eRepo;

	@Override
	public List<Department> findAllDepartments() {
		return repo.findAll();
		
	}

	@Override
	public Department findById(int id) {
		Optional<Department> departmentOpt = repo.findById(id);
		Department department = null;
		if (departmentOpt.isPresent()) {
			department = departmentOpt.get();
		}
		return department;
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		return repo.saveAndFlush(newDepartment);		
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Department> departmentOpt = repo.findById(id);
		if (departmentOpt.isPresent()) {
			Department department = departmentOpt.get();
			for (Job j : department.getJobs()) {
				j.removeJobType(department);
				jRepo.saveAndFlush(j);
			}
			for (Part p : department.getParts()) {
				p.removeJobType(department);
				pRepo.saveAndFlush(p);
			}
			for (Employee e : department.getStaff()) {
				e.removeJobType(department);
				eRepo.saveAndFlush(e);
			}
			repo.delete(department);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Department updateDepartment(Department newDepartment, int id) {
		Optional<Department> departmentOpt = repo.findById(id);
		Department update = null;
		if (departmentOpt.isPresent()) {
			update = departmentOpt.get();
			if (newDepartment.getName() != null) {update.setName(newDepartment.getName());	}
			if (newDepartment.getJobs() != null) {update.setJobs(newDepartment.getJobs());	}
			if (newDepartment.getParts() != null) {update.setParts(newDepartment.getParts());	}
			if (newDepartment.getStaff() != null) {update.setStaff(newDepartment.getStaff());	}
			
		}
		return repo.saveAndFlush(update);

	}
}
