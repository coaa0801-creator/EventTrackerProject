package com.skilldistillery.cheyenne.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartmentTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Department jobType;
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
		jobType = em.find(Department.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void testEntity() {
		assertNotNull(jobType);
	    assertEquals("Plumbing", jobType.getName());
	}
	@Test
	void testEntitytoJob() {
		assertNotNull(jobType);
		assertNotNull(jobType.getJobs());
		assertTrue(jobType.getJobs().size()>0);
		assertEquals("Carlson", jobType.getJobs().get(0).getName());
	}
	@Test
	void testEntitytoEmployee() {
		assertNotNull(jobType);
		assertNotNull(jobType.getStaff());
		assertTrue(jobType.getStaff().size()>0);
		assertEquals("David", jobType.getStaff().get(0).getFirstName());
	}
	@Test
	void testEntityToPart() {
		assertNotNull(jobType);
		assertNotNull(jobType.getParts());
		assertTrue(jobType.getParts().size()>0);
		assertEquals("8FT PVC 3\"", jobType.getParts().get(0).getName());
	}

}
