package com.skilldistillery.cheyenne.services;

import java.util.List;

import com.skilldistillery.cheyenne.entities.Address;

public interface AddressService {

	List<Address> findAllAddresss();

	Address findById(int id);

	Address createAddress(Address newAddress);

	boolean delete(int id);

	Address updateAddress(Address newAddress, int id);
}
