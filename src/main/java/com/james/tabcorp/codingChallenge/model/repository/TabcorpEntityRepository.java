package com.james.tabcorp.codingChallenge.model.repository;

import org.springframework.data.repository.CrudRepository;
import com.james.tabcorp.codingChallenge.model.entity.TabcorpEntity;

public interface TabcorpEntityRepository extends CrudRepository<TabcorpEntity, String>{

}
