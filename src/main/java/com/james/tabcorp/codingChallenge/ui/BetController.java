package com.james.tabcorp.codingChallenge.ui;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BetController {

	@RequestMapping(value = "/bet", method = RequestMethod.POST)
    public void process(@RequestBody String payload) throws Exception {
        System.out.println(payload);
    }
	
	@RequestMapping(value = "/bet1", method = RequestMethod.POST)
    public void process1(@RequestBody Bet bet) throws Exception {
        System.out.println(bet.getBetType() + ", " + bet.getCustomerId() + ", " + bet.getDateTime());
    }
}
