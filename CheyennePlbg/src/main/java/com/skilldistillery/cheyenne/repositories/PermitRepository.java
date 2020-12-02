package com.skilldistillery.cheyenne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.cheyenne.entities.Permit;

public interface PermitRepository extends JpaRepository<Permit, Integer> {

}
