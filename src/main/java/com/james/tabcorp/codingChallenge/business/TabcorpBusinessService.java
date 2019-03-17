package com.james.tabcorp.codingChallenge.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.james.tabcorp.codingChallenge.model.entity.BetEntity;
import com.james.tabcorp.codingChallenge.model.repository.BetRepository;
import com.james.tabcorp.codingChallenge.ui.Bet;

@Service
public class TabcorpBusinessService {

	private BetRepository repository;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public final static int MAXIMUM_INVESTMENT = 20000;
	
	public enum BET_TYPE {
		WIN, PLACE, TRIFECTA, DOUBLE, QUADDIE
	}
	
	public TabcorpBusinessService(BetRepository tabcorpRepository) {
		this.repository = tabcorpRepository;
	}
	
	public void createBet(Bet bet) throws IllegalArgumentException {
		Date dateTime = createDateFromDateString(bet.getDateTime());
		
        long currentTime = System.currentTimeMillis() - 3600; // Past means One hour before the current time.
        if (dateTime.before(new Date(currentTime)))
        	throw new IllegalArgumentException("Current time should not in the past.");
        
		if (bet.getInvestmentAmount() > MAXIMUM_INVESTMENT)
			throw new IllegalArgumentException("Investment Value is greater than Maximum Investment ($20,000)");
		
		// Converting String to enum to check if the bet type is within a list of valid bet types.
		// An exception will be thrown if the bet type is invalid.
		BET_TYPE betType = convertBetTypeToEnum(bet.getBetType());

		BetEntity entity = new BetEntity(dateTime, betType.toString(), bet.getPropNumber(), 
				bet.getCustomerId(), bet.getInvestmentAmount());

		this.repository.save(entity);
	}

	/**
	 * Calculate total investment based on specified bet type.
	 * 
	 * @param betType	A specified bet type.
	 * 
	 * @return Sum of investment per bet type.
	 */
	public double totalInvestmentPerBetType(String betType) {
		BET_TYPE bt = convertBetTypeToEnum(betType);
		
		List<BetEntity> be = repository.findByBetType(bt.toString());
		double total = be.stream().mapToDouble(o -> o.getInvestmentAmount()).sum();

		return total;
	}

	/**
	 * Calculate total investment based on specified customer Id.
	 * 
	 * @param customerId	A specified customer ID.
	 * 
	 * @return Sum of investment per customer Id.
	 */
	public double totalInvestmentPerCustomerId(int customerId) {
		List<BetEntity> be = repository.findByCustomerId(customerId);
		double total = be.stream().mapToDouble(o -> o.getInvestmentAmount()).sum();

		return total;
	}

	/**
	 * Total bet sold based on a specified bet type.
	 * 
	 * @param betType	A specified bet type.
	 *
	 * @return Total bet sold per bet type.
	 */
	public int totalBetSoldPerBetType(String betType) {
		BET_TYPE bt = convertBetTypeToEnum(betType);
		return repository.findByBetType(bt.toString()).size();
	}

	/**
	 * Return total bet sold per hour since the specified date.
	 * 
	 * @param date	A specified start date.
	 * @return total bet sold per hour.
	 */
	public int totalBetSoldPerHour(String date) {
		Date startDate = createDateFromDateString(date);
		Date endDate =  new Date(startDate.getTime() + 3600);
		
		return repository.findAllByDateTimeBetween(startDate, endDate).size();
	}

	/**
	 * Convert a bet type to enum. This function will throw an exception if the specified bet type is invalid.
	 * 
	 * @param betType	A specified bet type
	 * 
	 * @return Enum of {@link BET_TYPE} if the bet type is valid.
	 */
	private BET_TYPE convertBetTypeToEnum(String betType) {
		return BET_TYPE.valueOf(betType);
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
