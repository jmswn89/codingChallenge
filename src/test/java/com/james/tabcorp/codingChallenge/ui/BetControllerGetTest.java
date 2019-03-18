package com.james.tabcorp.codingChallenge.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BetControllerGetTest extends AbstractController {
	private final List<Bet> betData = new ArrayList<Bet>();

	@Override
	@Before
	public void setUp() {
		super.setUp();
		try {
			populateData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	@After
	public void tearDown()
	{
		super.tearDown();
	}

	private void populateData() throws Exception {
		String uri = "/bet";
		// First bet.
		Bet bet = new Bet();
		bet.setBetType("WIN");
		bet.setCustomerId(1);
		Date dateTime = new Date(System.currentTimeMillis());
		bet.setDateTime(DATE_FORMAT.format(dateTime));
		bet.setInvestmentAmount(100.0);
		bet.setPropNumber(10);
		betData.add(bet);

		String inputJson = super.mapToJson(bet);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		// Second Bet
		bet = new Bet();
		bet.setBetType("WIN");
		bet.setCustomerId(1);
		dateTime = new Date(System.currentTimeMillis());
		bet.setDateTime(DATE_FORMAT.format(dateTime));
		bet.setInvestmentAmount(200.0);
		bet.setPropNumber(11);
		betData.add(bet);

		inputJson = super.mapToJson(bet);
		mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		// Third bet.
		bet = new Bet();
		bet.setBetType("WIN");
		bet.setCustomerId(2);
		dateTime = new Date(System.currentTimeMillis());
		bet.setDateTime(DATE_FORMAT.format(dateTime));
		bet.setInvestmentAmount(300.0);
		bet.setPropNumber(12);
		betData.add(bet);

		inputJson = super.mapToJson(bet);
		mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		// Fourth bet.
		bet = new Bet();
		bet.setBetType("DOUBLE");
		bet.setCustomerId(2);
		dateTime = new Date(System.currentTimeMillis());
		bet.setDateTime(DATE_FORMAT.format(dateTime));
		bet.setInvestmentAmount(300.0);
		bet.setPropNumber(12);
		betData.add(bet);

		inputJson = super.mapToJson(bet);
		mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testFindAllReports() throws Exception {
		// Make sure we have all data have been populated.
		String uri = "/bets";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Bet[] results = super.mapFromJson(content, Bet[].class);
		assertEquals(results.length, betData.size());
		for (int i = 0; i < results.length; i++) {
			isEqual(results, i);
		}
	}
	
	@Test
	public void testTotalInvestmentPerBetType() throws Exception {
		// Make sure we have all data have been populated.
		String uri = "/report/totalInvestmentPerBetType/WIN";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Report result = super.mapFromJson(content, Report.class);
		List<Bet> bets = result.getBets();
		assertEquals(bets.size(), 3);
		assertTrue(result.getTotalInvestment() == 600.0);
		assertEquals(result.getTotalBets(), -1);
	}

	@Test
	public void testTotalInvestmentPerCustomerId() throws Exception {
		// Make sure we have all data have been populated.
		String uri = "/report/totalInvestmentPerCustomerId/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Report result = super.mapFromJson(content, Report.class);
		List<Bet> bets = result.getBets();
		assertEquals(bets.size(), 2);
		assertTrue(result.getTotalInvestment() == 600.0);
		assertEquals(result.getTotalBets(), -1);
	}
	
	@Test
	public void testTotalBetSoldPerBetType() throws Exception {
		// Make sure we have all data have been populated.
		String uri = "/report/totalBetSoldPerBetType/DOUBLE";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Report result = super.mapFromJson(content, Report.class);
		List<Bet> bets = result.getBets();
		assertEquals(bets.size(), 1);
		assertTrue(result.getTotalInvestment() == -1);
		assertEquals(result.getTotalBets(), 1);
	}

	
	@Test
	public void testTotalBetSoldPerHour() throws Exception {
		// Make sure we have all data have been populated.
		String uri = "/report/totalBetSoldPerHour/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Report result = super.mapFromJson(content, Report.class);
		List<Bet> bets = result.getBets();
		assertEquals(bets.size(), 4);
		assertTrue(result.getTotalInvestment() == -1);
		assertEquals(result.getTotalBets(), 4);
	}
	
	private void isEqual(Bet[] results, int index) {
		Bet result = results[index];
		Bet bet = betData.get(index);
		assertEquals(bet.getBetType(), result.getBetType());
		assertEquals(bet.getCustomerId(), result.getCustomerId());
		assertEquals(bet.getPropNumber(), result.getPropNumber());
		assertEquals(bet.getInvestmentAmount(), result.getInvestmentAmount());
		assertEquals(bet.getDateTime(), result.getDateTime());
	}
}
