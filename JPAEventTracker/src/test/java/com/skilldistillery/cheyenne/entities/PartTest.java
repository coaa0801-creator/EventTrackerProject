package com.skilldistillery.cheyenne.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Part part;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("CheyennePU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		part = em.find(Part.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void testEntity() {
		assertNotNull(part);
		assertEquals("8FT PVC 3\"", part.getName());
	}
	@Test
	void testEntityToDepartment() {
		assertNotNull(part);
		assertEquals("Plumbing", part.getJobType().getName());
	}
	@Test
	void testEntityToJob() {
		assertNotNull(part);
		assertEquals("Carlson", part.getJob().getName());
	}

}
