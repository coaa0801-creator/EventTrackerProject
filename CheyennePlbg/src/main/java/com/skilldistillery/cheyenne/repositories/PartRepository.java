package com.skilldistillery.cheyenne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.cheyenne.entities.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {

}
