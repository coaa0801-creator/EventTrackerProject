package com.skilldistillery.cheyenne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.cheyenne.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
