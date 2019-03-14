package com.james.tabcorp.codingChallenge.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.james.tabcorp.codingChallenge.model.entity.TabcorpEntity;
import com.james.tabcorp.codingChallenge.model.repository.TabcorpEntityRepository;
import com.james.tabcorp.codingChallenge.ui.Bet;

@Service
public class TabcorpBusinessService {

	private TabcorpEntityRepository tabcorpRepository;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public TabcorpBusinessService(TabcorpEntityRepository tabcorpRepository) {
		this.tabcorpRepository = tabcorpRepository;
	}
	
	public void createBet(Bet bet) throws IllegalArgumentException {
		TabcorpEntity entity = new TabcorpEntity(null, null, null, null, null, null);
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
