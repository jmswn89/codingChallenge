package com.james.tabcorp.codingChallenge.ui;

import java.util.List;

import javax.persistence.Id;

/**
 * This class represents a generated report and the REST client will use it. 
 * It holds a list of {@link Bet}, total investment and total bets. The list of bets is a list of bets found. 
 * Total investments is a total of investments for a list of bets found. Total bets is a total of bets found 
 * for a list of bets found. If the generated report produces a total of investment, then the total investment 
 * value is not -1 and total bets is -1. On the other hand, if the generated report has a total bets, 
 * then the total bets value is not -1 and the total investment is -1.
 * 
 * @author James Jayaputera
 *
 */
public class Report {
	@Id
	private Long betId;
	
	private List<Bet> bets;
	private double totalInvestment;
	private int totalBets;
	
	public Report() {
		
	}

	public Report(List<Bet> bets, double totalInvestment) {
		this.bets = bets;
		this.totalInvestment = totalInvestment;
		this.totalBets = -1;
	}

	public Report(List<Bet> bets, int totalBets) {
		this.bets = bets;
		this.totalBets = totalBets;
		this.totalInvestment = -1;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

	public double getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(double totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	public int getTotalBets() {
		return totalBets;
	}

	public void setTotalBets(int totalBets) {
		this.totalBets = totalBets;
	}
	
}
