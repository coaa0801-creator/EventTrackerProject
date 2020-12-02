package com.skilldistillery.cheyenne.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Address;
import com.skilldistillery.cheyenne.entities.Customer;
import com.skilldistillery.cheyenne.entities.Job;
import com.skilldistillery.cheyenne.repositories.AddressRepository;
import com.skilldistillery.cheyenne.repositories.CustomerRepository;
import com.skilldistillery.cheyenne.repositories.JobRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository repo;
	@Autowired
	private JobRepository jRepo;
	@Autowired
	private AddressRepository aRepo;

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> allCust = repo.findAll();
		List<Customer> activeList = new ArrayList<Customer>();
	for (Customer customer : allCust) {
		customer.getAddresses().size();
		customer.getJobs().size();
		if(customer.getActive() == true) {
			activeList.add(customer);
		}
	}
		return activeList;
		
	}

	@Override
	public Customer findById(int id) {
		Optional<Customer> customerOpt = repo.findById(id);
		Customer customer = null;
		if (customerOpt.isPresent()) {
			customer = customerOpt.get();
			if (customer.getActive() == false) {
				return null;
			}
		}
		return customer;
	}

	@Override
	public Customer createCustomer(Customer newCustomer) {
		if(newCustomer.getAddresses() != null){
		for (Address a : newCustomer.getAddresses()) {
//			if(a.getCustomer() != newCustomer) {
//				a.setCustomer(newCustomer);
//			}
			aRepo.saveAndFlush(a);
		}
		}
		return repo.saveAndFlush(newCustomer);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Customer> customerOpt = repo.findById(id);
		if (customerOpt.isPresent()) {
			Customer cust = customerOpt.get();
			for (Job j : cust.getJobs()) {
				j.removeCustomer(cust);
				jRepo.saveAndFlush(j);
			}
			for (Address a : cust.getAddresses()) {
//				a.removeCustomer(cust);
				aRepo.saveAndFlush(a);
			}
			cust.setActive(false);
			repo.save(cust);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Customer updateCustomer(Customer newCustomer, int id) {
		Optional<Customer> customerOpt = repo.findById(id);
		Customer update = null;
		if (customerOpt.isPresent()) {
			update = customerOpt.get();
			if (newCustomer.getFirstName() != null) {update.setFirstName(newCustomer.getFirstName());	}
			if (newCustomer.getLastName() != null) {update.setLastName(newCustomer.getLastName());	}
			if (newCustomer.getEmail() != null) {update.setEmail(newCustomer.getEmail());	}
			if (newCustomer.getCompany() != null) {update.setCompany(newCustomer.getCompany());	}
			if (newCustomer.getAddresses() != update.getAddresses()) {
				
				for (Address a : newCustomer.getAddresses()) {
//					if(a.getCustomer() != newCustomer) {
//						a.setCustomer(newCustomer);
//					}
					aRepo.saveAndFlush(a);
				}
				update.setAddresses(newCustomer.getAddresses());	}
			if (newCustomer.getJobs() != update.getJobs()) {update.setJobs(newCustomer.getJobs());
		}
		

	}
		return repo.saveAndFlush(update);
}
}
