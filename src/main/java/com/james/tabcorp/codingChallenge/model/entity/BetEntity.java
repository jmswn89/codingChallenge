package com.james.tabcorp.codingChallenge.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tabcorp")
public class BetEntity {
	@Id
	@Column(name="id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long betId;

	@Column
	private Date DateTime;
	
	@Column
	private String betType;
	
	@Column
	private Integer propNumber;
	
	@Column
	private Integer customerId;
	
	@Column
	private Double investmentAmount;

	public BetEntity(Date dateTime, String betType, Integer propNumber, Integer customerId,
			Double investmentAmount) {
		DateTime = dateTime;
		this.betType = betType;
		this.propNumber = propNumber;
		this.customerId = customerId;
		this.investmentAmount = investmentAmount;
	}

	public Long getBetId() {
	    return betId;
	}

	public void setBetId(Long betId) {
		this.betId = betId;
	}

	public Date getDateTime() {
		return DateTime;
	}

	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
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
}
