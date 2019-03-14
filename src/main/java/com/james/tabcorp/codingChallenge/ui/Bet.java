package com.james.tabcorp.codingChallenge.ui;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Bet {

	@Id
	@NotNull
	private UUID betId;
	
	@NotNull
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private String dateTime;

	@NotNull
	private String betType;
	
	@NotNull
	private Integer propNumber;
	
	@NotNull
	private Integer customerId;
	
	@NotNull
	private Double investmentAmount;

	public Bet(@NotNull String dateTime, @NotNull String betType, @NotNull Integer propNumber,
			@NotNull Integer customerId, @NotNull Double investmentAmount) {
		super();
		this.betId = UUID.randomUUID();
		this.dateTime = dateTime;
		this.betType = betType;
		this.propNumber = propNumber;
		this.customerId = customerId;
		this.investmentAmount = investmentAmount;
	}

	public UUID getBetId() {
		return betId;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public Integer getPropNumber() {
		return propNumber;
	}

	public void setPropNumber(Integer propNumber) {
		this.propNumber = propNumber;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(Double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}
