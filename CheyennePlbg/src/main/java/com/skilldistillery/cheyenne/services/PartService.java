package com.skilldistillery.cheyenne.services;

import java.util.List;

import com.skilldistillery.cheyenne.entities.Part;

public interface PartService {

	List<Part> findAllParts();

	Part findById(int id);

	Part createPart(Part newPart);

	boolean delete(int id);

	Part updatePart(Part newPart, int id);
}
