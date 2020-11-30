package com.skilldistillery.cheyenne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Customer;
import com.skilldistillery.cheyenne.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository repo;

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> allCust = repo.findAll();
	for (Customer customer : allCust) {
		customer.getAddresses().size();
	}
		return allCust;
		
	}

	@Override
	public Customer findById(int id) {
		Optional<Customer> customerOpt = repo.findById(id);
		Customer customer = null;
		if (customerOpt.isPresent()) {
			customer = customerOpt.get();
		}
		return customer;
	}

	@Override
	public Customer createCustomer(Customer newCustomer) {
		return repo.saveAndFlush(newCustomer);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Customer> customerOpt = repo.findById(id);
		if (customerOpt.isPresent()) {
			Customer cust = customerOpt.get();
			cust.setActive(0);
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
			if (newCustomer.getAddresses() != null) {update.setAddresses(newCustomer.getAddresses());	}
			if (newCustomer.getJobs().size() > update.getJobs().size()) {update.addJob(newCustomer.getJobs().get(newCustomer.getJobs().size() - 1));}	
		}
		return repo.saveAndFlush(update);

	}
}
