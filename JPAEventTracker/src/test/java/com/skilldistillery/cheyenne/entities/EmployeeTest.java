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

class EmployeeTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Employee staff;
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
		staff = em.find(Employee.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void testEntity() {
		assertNotNull(staff);
		assertEquals("David", staff.getFirstName());
	}
	@Test
	void testEntitytoAddress() {
		assertNotNull(staff);
		assertEquals("25 RB St", staff.getAddress().getAddress());
	}
	@Test
	void testEntitytoJob() {
		assertNotNull(staff);
		assertNotNull(staff.getJobs());
		assertTrue(staff.getJobs().size()>0);
		assertEquals("Carlson", staff.getJobs().get(0).getName());
	}
	@Test
	void testEntitytoDepartment() {
		assertNotNull(staff);
		assertNotNull(staff.getJobTypes());
		assertTrue(staff.getJobTypes().size()>0);
		assertEquals("Plumbing", staff.getJobTypes().get(0).getName());
	}
	
	
	

}
