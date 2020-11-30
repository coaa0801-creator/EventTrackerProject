package com.skilldistillery.cheyenne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.cheyenne.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
