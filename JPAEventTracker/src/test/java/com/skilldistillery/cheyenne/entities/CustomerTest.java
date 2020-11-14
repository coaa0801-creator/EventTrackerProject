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

class CustomerTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Customer cust;
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
		cust = em.find(Customer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void testEntity() {
		assertNotNull(cust);
		assertEquals("Kyle@gmail.com", cust.getEmail());
	}
	@Test
	void testEntitytoAddress() {
		assertNotNull(cust);
		assertEquals("2000 Made Up Job", cust.getAddress().getAddress());
	}
	@Test
	void testEntitytoJob() {
		assertNotNull(cust);
		assertNotNull(cust.getJobs());
		assertTrue(cust.getJobs().size()>0);
		assertEquals("Carlson", cust.getJobs().get(0).getName());
		
	}
	
	

}
