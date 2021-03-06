package com.james.tabcorp.codingChallenge.model.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.james.tabcorp.codingChallenge.model.entity.BetEntity;

/**
 * The custom repository abstraction interface.
 * 
 * @author James Jayaputera
 *
 */
public interface BetRepository extends CrudRepository<BetEntity, Long>{

	public List<BetEntity> findByBetType(String betType);
	public List<BetEntity> findByCustomerId(Integer customerId);
	public List<BetEntity> findAllByDateTimeBetween(Date start, Date end);
}
