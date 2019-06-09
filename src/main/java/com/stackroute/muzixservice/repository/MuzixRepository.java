package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Muzix;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MuzixRepository extends MongoRepository<Muzix,Integer> {

}
