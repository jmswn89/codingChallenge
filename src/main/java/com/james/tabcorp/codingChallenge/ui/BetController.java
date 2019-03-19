package com.james.tabcorp.codingChallenge.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.james.tabcorp.codingChallenge.business.BusinessService;

/**
 * This class contains all endpoints which will be called by REST client.
 * 
 * @author James Jayaputera
 *
 */
@RestController
public class BetController {
	private BusinessService businessService;
	private String errorMessage = "";

	/**
	 * Constructor
	 * 
	 * @param businessService A {@link BusinessService}.
	 */
	@Autowired
	public BetController(BusinessService businessService) {
		this.businessService = businessService;
	}

	/**
	 * An endpoint to create a bet.
	 * 
	 * @param bet	An instance of {@link Bet}.
	 */
	@RequestMapping(value = "/bet", method = RequestMethod.POST)
    public void createBet(@RequestBody Bet bet) {
        try {
        	this.businessService.createBet(bet);
        }
        catch (Exception ex) 
        {
        	errorMessage = ex.getMessage();
        	throw ex;
        }
    }
	
	/**
	 * An endpoint to retrieve all bets available in database.
	 * 
	 * @return a list of {@link Bet}.
	 */
	@RequestMapping(value = "/bets", method = RequestMethod.GET)
    public List<Bet> getAllBets() {
		List<Bet> bets = new ArrayList<Bet>();
        try {
        	bets = this.businessService.findAllBets();
        }
        catch (Exception ex) 
        {
        	errorMessage = ex.getMessage();
        	throw ex;
        }
        return bets;
    }
	
	/**
	 * An endpoint to generate a report which contains a list of bets and its total investment based on a specified bet type.
	 * 
	 * @param betType A bet type (WIN / PLACE / TRIFECTA / DOUBLE / QUADDIE).
	 * 
	 * @return {@link Report} instance.
	 */
	@RequestMapping(value = "/report/totalInvestmentPerBetType/{betType}", method = RequestMethod.GET)
    public Report getTotalInvestmentPerBetType(@PathVariable String betType) {
		Report report = new Report();
        try {
        	report = this.businessService.totalInvestmentPerBetType(betType);
        }
        catch (Exception ex) 
        {
        	errorMessage = ex.getMessage();
        	throw ex;
        }
        return report;
	}
	
	/**
	 * An endpoint to generate a report which contains a list of bets and its total investment based on a customer ID.
	 * 
	 * @param customerId A customer ID.
	 * 
	 * @return {@link Report}.
	 */
	@RequestMapping(value = "/report/totalInvestmentPerCustomerId/{customerId}", method = RequestMethod.GET)
    public Report getTotalInvestmentPerCustomerId(@PathVariable int customerId) {
		Report report = new Report();
        try {
        	report = this.businessService.totalInvestmentPerCustomerId(customerId);
        }
        catch (Exception ex) 
        {
        	errorMessage = ex.getMessage();
        	throw ex;
        }
        return report;
	}

	/**
	 * An endpoint to generate a report which contains a list of bets and total bets sold based on a specified bet type.
	 * 
	 * @param betType A bet type (WIN / PLACE / TRIFECTA / DOUBLE / QUADDIE)
	 * 
	 * @return {@link Report}.
	 */
	@RequestMapping(value = "/report/totalBetSoldPerBetType/{betType}", method = RequestMethod.GET)
    public Report getTotalBetSoldPerBetType(@PathVariable String betType) {
		Report report = new Report();
        try {
        	report = this.businessService.totalBetSoldPerBetType(betType);
        }
        catch (Exception ex) 
        {
        	errorMessage = ex.getMessage();
        	throw ex;
        }
        return report;
	}

	/**
	 * An endpoint to generate a list of bets and the total bets since one hour from the current time.
	 * 
	 * @return {@link Report}.
	 */
	@RequestMapping(value = "/report/totalBetSoldPerHour", method = RequestMethod.GET)
    public Report getTotalBetSoldPerHour() {
		Report report = new Report();
        try {
        	report = this.businessService.totalBetSoldPerHour();
        }
        catch (Exception ex) 
        {
        	errorMessage = ex.getMessage();
        	throw ex;
        }
        return report;
	}

	/**
	 * To remove all database records.
	 * 
	 */
	@RequestMapping(value = "/cleanDatabase", method = RequestMethod.DELETE)
    public void cleanDatabase() {
        try {
        	this.businessService.cleanDatabase();
        }
        catch (Exception ex) 
        {
        	errorMessage = ex.getMessage();
        	throw ex;
        }
    }
	
	@ExceptionHandler({IllegalArgumentException.class})
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value(), errorMessage);
	}
	
}
