package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;

import java.util.List;

public interface MuzixServices {
    public Muzix saveMuzixs(Muzix muzix) throws MuzixAlreadyExistsException;
    public List<Muzix> getAllMuzixs();
    public Muzix findById(int trackId) throws TrackNotFoundException;
    public Muzix findByName(String trackName) throws TrackNotFoundException;
    public Muzix deleteById(int trackId) throws TrackNotFoundException;
    public Muzix updateMuzixById(int trackId,Muzix muzix) throws TrackNotFoundException;
}
