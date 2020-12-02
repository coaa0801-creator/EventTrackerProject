package com.skilldistillery.cheyenne.services;

import java.util.List;

import com.skilldistillery.cheyenne.entities.Permit;

public interface PermitService {

	List<Permit> findAllPermits();

	Permit findById(int id);

	Permit createPermit(Permit newPermit);

	boolean delete(int id);

	Permit updatePermit(Permit newPermit, int id);
}
