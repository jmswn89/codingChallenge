package com.james.tabcorp.codingChallenge.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bet")
public class BetEntity {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long betId;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	@Column
	private String betType;
	
	@Column
	private Integer propNumber;
	
	@Column
	private Integer customerId;
	
	@Column
	private Double investmentAmount;

	public BetEntity() {
		
	}

	public Long getBetId() {
	    return betId;
	}

	public void setBetId(Long betId) {
		this.betId = betId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
