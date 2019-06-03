package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MuzixRepository extends JpaRepository<Muzix,Integer> {
    @Query("select u from Muzix u where u.trackName=:trackName")
    public Muzix findByName(@Param("trackName") String trackName) throws TrackNotFoundException;

}
