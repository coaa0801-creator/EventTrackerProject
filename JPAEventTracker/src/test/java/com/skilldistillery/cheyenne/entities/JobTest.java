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

class JobTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Job job;
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
		job = em.find(Job.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void testEntity() {
		assertNotNull(job);
		assertEquals("Carlson", job.getName());
	}
	@Test
	void testEntityToCustomer() {
		assertNotNull(job);
		assertEquals("Kyle", job.getCustomer().getFirstName());
	}
	@Test
	void testEntityToAddress() {
		assertNotNull(job);
		assertEquals("2000 Made Up Job", job.getAddress().getAddress());
	}
	@Test
	void testEntityToEmployee() {
		assertNotNull(job);
		assertNotNull(job.getStaff());
		assertTrue(job.getStaff().size()>0);
		assertEquals("David", job.getStaff().get(0).getFirstName());
	}
	@Test
	void testEntityToDepartment() {
		assertNotNull(job);
		assertNotNull(job.getJobTypes());
		assertTrue(job.getJobTypes().size()>0);
		assertEquals("Plumbing", job.getJobTypes().get(0).getName());
	}
	@Test
	void testEntityToPart() {
		assertNotNull(job);
		assertNotNull(job.getParts());
		assertTrue(job.getParts().size()>0);
		assertEquals("8FT PVC 3\"", job.getParts().get(0).getName());
	}
	@Test
	void testEntityToPermit() {
		assertNotNull(job);
		assertNotNull(job.getPermits());
		assertTrue(job.getPermits().size()>0);
		assertEquals("A-15163518", job.getPermits().get(0).getIdentifier());
	}

}
