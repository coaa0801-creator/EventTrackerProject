package com.skilldistillery.cheyenne.services;

import java.util.List;

import com.skilldistillery.cheyenne.entities.Customer;

public interface CustomerService {

	List<Customer> findAllCustomers();

	Customer findById(int id);

	Customer createCustomer(Customer newCustomer);

	boolean delete(int id);

	Customer updateCustomer(Customer newCustomer, int id);
}
