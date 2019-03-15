package com.james.tabcorp.codingChallenge.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.james.tabcorp.codingChallenge.model.entity.BetEntity;
import com.james.tabcorp.codingChallenge.model.repository.BetRepository;
import com.james.tabcorp.codingChallenge.ui.Bet;

@Service
public class TabcorpBusinessService {

	private BetRepository tabcorpRepository;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public TabcorpBusinessService(BetRepository tabcorpRepository) {
		this.tabcorpRepository = tabcorpRepository;
	}
	
	public void createBet(Bet bet) throws IllegalArgumentException {
		Date dateTime = createDateFromDateString(bet.getDateTime());
		BetEntity entity = new BetEntity(new java.sql.Date(dateTime.getTime()), bet.getBetType(), bet.getPropNumber(), 
				bet.getCustomerId(), bet.getInvestmentAmount());
		this.tabcorpRepository.save(entity);
	}

	private Date createDateFromDateString(String dateString){
        Date date = null;
        if (null != dateString) {
            try {
                date = DATE_FORMAT.parse(dateString);
            }catch(ParseException pe){
                date = new Date();
            }
        } else {
            date = new Date();
        }
        
        return date;
    }
}
