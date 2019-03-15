package com.james.tabcorp.codingChallenge.model.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.james.tabcorp.codingChallenge.model.entity.BetEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BetRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BetRepository repository;
	
	@Test
	public void testFindByBetType() {
		BetEntity be = new BetEntity(new java.sql.Date(System.currentTimeMillis()), "WIN", 123, 1, 10.0);
		entityManager.persistAndFlush(be);
		
		List<BetEntity> results =  repository.findByBetType("WIN");
		assertEquals(results.size(), 1);
		BetEntity result = results.get(0);
		assertTrue(be.getCustomerId() == result.getCustomerId());
		assertTrue(be.getInvestmentAmount() == result.getInvestmentAmount());
	}
	
	@Test
	public void testFindByCustomerId() {
		BetEntity be = new BetEntity(new java.sql.Date(System.currentTimeMillis()), "WIN", 123, 1, 10.0);
		entityManager.persistAndFlush(be);
		be = new BetEntity(new java.sql.Date(System.currentTimeMillis()), "WIN", 123, 1, 100.0);
		entityManager.persistAndFlush(be);
		
		List<BetEntity> results =  repository.findByCustomerId(1);
		assertEquals(results.size(), 2);
		BetEntity result = results.get(0);
		assertTrue(be.getCustomerId() == result.getCustomerId());
		assertTrue(be.getInvestmentAmount() == 100.0);
	}
}
