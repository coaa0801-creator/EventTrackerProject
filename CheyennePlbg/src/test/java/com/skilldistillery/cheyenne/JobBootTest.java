package com.skilldistillery.cheyenne;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.cheyenne.entities.Job;
import com.skilldistillery.cheyenne.repositories.JobRepository;
@SpringBootTest
class JobBootTest {

	@Autowired
	private JobRepository repo;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	void testFindByID() {
		Optional<Job> addrOpt = repo.findById(1);
		assertTrue(addrOpt.isPresent());
		Job addr = addrOpt.get();
		assertEquals("Carlson", addr.getName());
		
	}
}