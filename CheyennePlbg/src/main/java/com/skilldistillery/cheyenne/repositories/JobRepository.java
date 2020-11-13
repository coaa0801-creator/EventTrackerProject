package com.skilldistillery.cheyenne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.cheyenne.entities.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
