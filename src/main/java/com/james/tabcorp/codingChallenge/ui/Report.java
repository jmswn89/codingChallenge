package com.james.tabcorp.codingChallenge.ui;

import java.util.List;

import javax.persistence.Id;

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
