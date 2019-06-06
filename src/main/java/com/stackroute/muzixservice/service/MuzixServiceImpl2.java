package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuzixServiceImpl2 implements MuzixServices {

    private MuzixRepository muzixRepository;

    @Autowired
    public MuzixServiceImpl2(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public Muzix saveMuzixs(Muzix muzix) throws MuzixAlreadyExistsException {
        return null;
    }

    @Override
    public List<Muzix> getAllMuzixs() {
        return null;
    }

    @Override
    public Muzix findById(int trackId) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Muzix findByName(String trackName) throws TrackNotFoundException {
        return null;
    }

    @Override
    public List<Muzix> deleteById(int trackId) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Muzix updateMuzix(Muzix muzix) throws TrackNotFoundException {
        return null;
    }
}
