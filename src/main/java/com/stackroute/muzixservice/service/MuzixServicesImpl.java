package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class MuzixServicesImpl implements com.stackroute.muzixservice.service.MuzixServices {
    //@Autowired
    private MuzixRepository muzixRepository;

    @Autowired
    public MuzixServicesImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public Muzix saveMuzixs(Muzix muzix) throws MuzixAlreadyExistsException {
      if(muzixRepository.existsById(muzix.getTrackId()))
      {
          throw new MuzixAlreadyExistsException("Muzix already exists");
      }
      Muzix savedMuzix=muzixRepository.save(muzix);
      if(savedMuzix==null)
      {
          throw new MuzixAlreadyExistsException("Muzix already exists");
      }
        return savedMuzix;
    }

    @Override
    public List<Muzix> getAllMuzixs() {
        return muzixRepository.findAll();
    }

    @Override
    public Muzix findById(int trackId) throws TrackNotFoundException {
        Muzix muzix =null;
        if(muzixRepository.existsById(trackId))
        {
            muzix=muzixRepository.findById(trackId).get();
            if(muzix==null){
                throw new TrackNotFoundException("Track Not Found");
            }
        }
        return muzix;
    }

    @Override
    public Muzix findByName(String trackName) throws TrackNotFoundException{
        Muzix muzix=null;
        muzix=muzixRepository.findByName(trackName);
        if(muzix == null){
            throw new TrackNotFoundException("Track not found");
        }
        return muzix;
    }

    @Override
    public Muzix deleteById(int trackId) throws TrackNotFoundException {
        if(muzixRepository.existsById(trackId))
        {
            muzixRepository.deleteById(trackId);
        }
        else
        {
            throw new TrackNotFoundException("Track not found");
        }
        return null;
    }

    public Muzix updateMuzixById(int trackId, Muzix muzix) throws  TrackNotFoundException{
        if(muzixRepository.existsById(trackId)) {
            muzix.setComments(muzix.getComments());
        }
        else
        {
            throw new TrackNotFoundException("Track not found");
        }
        Muzix muzix1=muzixRepository.save(muzix);
        return  muzix1;
    }
}
