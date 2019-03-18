package com.james.tabcorp.codingChallenge.model.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
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
		BetEntity be = new BetEntity();
		be.setDateTime(new Date(System.currentTimeMillis()));
		be.setBetType("WIN");
		be.setPropNumber(123);
		be.setCustomerId(1);
		be.setInvestmentAmount(10.0);
		entityManager.persistAndFlush(be);
		
		List<BetEntity> results =  repository.findByBetType("WIN");
		assertEquals(results.size(), 1);
		BetEntity result = results.get(0);
		assertTrue(be.getCustomerId() == result.getCustomerId());
		assertTrue(be.getInvestmentAmount() == result.getInvestmentAmount());
		
		// Not found
		results =  repository.findByBetType("PLACE");
		assertEquals(results.size(), 0);
	}
	
	@Test
	public void testFindByCustomerId() {
		BetEntity be = new BetEntity();
		be.setDateTime(new Date(System.currentTimeMillis()));
		be.setBetType("WIN");
		be.setPropNumber(123);
		be.setCustomerId(1);
		be.setInvestmentAmount(10.0);
		entityManager.persistAndFlush(be);

		be = new BetEntity();
		be.setDateTime(new Date(System.currentTimeMillis()));
		be.setBetType("WIN");
		be.setPropNumber(123);
		be.setCustomerId(1);
		be.setInvestmentAmount(100.0);
		entityManager.persistAndFlush(be);
		
		be = new BetEntity();
		be.setDateTime(new Date(System.currentTimeMillis()));
		be.setBetType("WIN");
		be.setPropNumber(123);
		be.setCustomerId(12);
		be.setInvestmentAmount(100.0);
		entityManager.persistAndFlush(be);
		entityManager.flush();
		
		List<BetEntity> results =  repository.findByCustomerId(1);
		assertEquals(results.size(), 2);
		BetEntity result = results.get(0);
		assertTrue(result.getCustomerId() == 1);
		assertTrue(be.getInvestmentAmount() == 100.0);
		
		// Not found
		results =  repository.findByCustomerId(2);
		assertEquals(results.size(), 0);
	}

	@Test
	public void testFindByDateTime() {
		Long currentTime = System.currentTimeMillis();
		Date dCurrTime = new Date(currentTime);
		BetEntity be1 = new BetEntity();
		be1.setDateTime(dCurrTime);
		be1.setBetType("WIN");
		be1.setPropNumber(123);
		be1.setCustomerId(1);
		be1.setInvestmentAmount(10.0);	
		entityManager.persistAndFlush(be1);

		Long currentTime2 = currentTime + (1 * 600);
		Date dCurrTime2 = new Date(currentTime2);
		BetEntity be2 = new BetEntity();
		be2.setDateTime(dCurrTime2);
		be2.setBetType("WIN");
		be2.setPropNumber(123);
		be2.setCustomerId(1);
		be2.setInvestmentAmount(200.0);
		entityManager.persistAndFlush(be2);

		Long currentTime3 = currentTime2 + 5600 * 1000;
		BetEntity be3 = new BetEntity();
		be3.setDateTime(new Date(currentTime3));
		be3.setBetType("WIN");
		be3.setPropNumber(123);
		be3.setCustomerId(1);
		be3.setInvestmentAmount(10.0);
		entityManager.persistAndFlush(be3);
		
		Long oneHourFromCurrentTime = currentTime + (1 * 3600 * 1000);
		List<BetEntity> results =  repository.findAllByDateTimeBetween(dCurrTime, new Date(oneHourFromCurrentTime));
		assertEquals(results.size(), 2);
		assertTrue(results.get(0).getDateTime().equals(dCurrTime));
		assertTrue(results.get(1).getDateTime().equals(dCurrTime2));
	}
}
