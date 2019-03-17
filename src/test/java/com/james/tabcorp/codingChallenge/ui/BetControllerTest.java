package com.james.tabcorp.codingChallenge.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BetControllerTest extends AbstractController {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void createBet() throws Exception {
		String uri = "/bet";
		Bet bet = new Bet();
		bet.setBetId(1L);
		bet.setBetType("WIN");
		bet.setCustomerId(1);
		Date dateTime = new Date(System.currentTimeMillis());
		bet.setDateTime(DATE_FORMAT.format(dateTime));
		bet.setInvestmentAmount(100.0);
		bet.setPropNumber(10);

		String inputJson = super.mapToJson(bet);
		System.out.println(inputJson);

		MvcResult  mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();;

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void createInvalidBetType() throws Exception {
		String uri = "/bet";
		Bet bet = new Bet();
		bet.setBetId(1L);
		bet.setBetType("WINNER");
		bet.setCustomerId(1);
		Date dateTime = new Date(System.currentTimeMillis());
		bet.setDateTime(DATE_FORMAT.format(dateTime));
		bet.setInvestmentAmount(100.0);
		bet.setPropNumber(10);

		String inputJson = super.mapToJson(bet);
		System.out.println(inputJson);

		MvcResult  mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String errorMessage = mvcResult.getResponse().getErrorMessage();
		assertTrue(errorMessage.contains("Unknown bet type"));
	}
	
	@Test
	public void createInvalidInvestmentAmount() throws Exception {
		String uri = "/bet";
		Bet bet = new Bet();
		bet.setBetId(1L);
		bet.setBetType("DOUBLE");
		bet.setCustomerId(1);
		Date dateTime = new Date(System.currentTimeMillis());
		bet.setDateTime(DATE_FORMAT.format(dateTime));
		bet.setInvestmentAmount(31000.0);
		bet.setPropNumber(10);

		String inputJson = super.mapToJson(bet);
		System.out.println(inputJson);

		MvcResult  mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String errorMessage = mvcResult.getResponse().getErrorMessage();
		assertTrue(errorMessage.contains("Investment Value is greater than Maximum Investment ($20,000)"));
	}

	@Test
	public void createInvalidDateTime() throws Exception {
		String uri = "/bet";
		Bet bet = new Bet();
		bet.setBetId(1L);
		bet.setBetType("WIN");
		bet.setCustomerId(1);
		// The date time is 2 hours before current time.
		Date dateTime = new Date(System.currentTimeMillis() - 7200);
		bet.setDateTime(DATE_FORMAT.format(dateTime));
		bet.setInvestmentAmount(3100.0);
		bet.setPropNumber(10);

		String inputJson = super.mapToJson(bet);
		System.out.println(inputJson);

		MvcResult  mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertNotEquals(200, status);
		assertEquals(400, status);
		MockHttpServletResponse response = mvcResult.getResponse();
		String errorMessage = response.getErrorMessage();
		assertTrue(errorMessage.contains("Current time should not in the past."));
	}
}
