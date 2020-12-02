package com.skilldistillery.cheyenne.services;

import java.util.List;

import com.skilldistillery.cheyenne.entities.Department;

public interface DepartmentService {

	List<Department> findAllDepartments();

	Department findById(int id);

	Department createDepartment(Department newDepartment);

	boolean delete(int id);

	Department updateDepartment(Department newDepartment, int id);
}
