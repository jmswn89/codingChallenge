package com.james.tabcorp.codingChallenge.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.james.tabcorp.codingChallenge.business.BusinessService;

@RestController
public class BetController {
	private BusinessService businessService;
	private String errorMessage = "";

	@Autowired
	public BetController(BusinessService businessService) {
		this.businessService = businessService;
	}

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
		System.out.println("hello world");
	    response.sendError(HttpStatus.BAD_REQUEST.value(), errorMessage);
	}
	
}
