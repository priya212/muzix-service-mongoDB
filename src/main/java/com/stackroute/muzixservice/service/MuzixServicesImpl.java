package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "muzix")
@Service
@Primary
public class MuzixServicesImpl implements com.stackroute.muzixservice.service.MuzixServices {
    private MuzixRepository muzixRepository;

    @Autowired
    public MuzixServicesImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Autowired
    private Environment environment;

    @Override
    @CacheEvict(allEntries = true)
    public Muzix saveMuzixs(Muzix muzix) throws MuzixAlreadyExistsException {
      if(muzixRepository.existsById(muzix.getTrackId())) {
          throw new MuzixAlreadyExistsException("${spring.muzix.alreadyExistException}");
      }
      Muzix savedMuzix=muzixRepository.save(muzix);
      if(savedMuzix == null) {
          throw new MuzixAlreadyExistsException("${spring.muzix.alreadyExistException}");
      }
        return savedMuzix;
    }

    public void simulateDelay()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    @Cacheable("muzix")
    public List<Muzix> getAllMuzixs() {
        simulateDelay();
        List<Muzix> muzixList=muzixRepository.findAll();
        return  muzixList;
    }

    @Override
    public Muzix findById(int trackId) throws TrackNotFoundException {
        Muzix muzix =null;
        if(muzixRepository.existsById(trackId)) {
            muzix=muzixRepository.findById(trackId).get();
        }
        return muzix;
    }

    @Override
    public Muzix findByName(String trackName) throws TrackNotFoundException{
        Muzix muzix=null;
        muzix=muzixRepository.findByName(trackName);
        if(muzix == null){
            throw new TrackNotFoundException(environment.getProperty("spring.muzix.trackException"));
        }
        return muzix;
    }


    @Override
    @CacheEvict(allEntries = true)
    public List<Muzix> deleteById(int trackId) throws TrackNotFoundException {
       Optional optional=muzixRepository.findById(trackId);
       if(optional.isPresent()) {
           muzixRepository.deleteById(trackId);
       }
       else {
           throw new TrackNotFoundException(environment.getProperty("spring.muzix.trackException"));
       }
       return  muzixRepository.findAll();
    }

    @Override
    @CacheEvict(allEntries = true)
    public Muzix updateMuzix(Muzix muzix1) throws  TrackNotFoundException{
         Muzix muzix=muzixRepository.getOne(muzix1.getTrackId());
         if(muzixRepository.existsById(muzix1.getTrackId())){
            muzix.setComments(muzix1.getComments());
            muzix=muzixRepository.save(muzix);
        }
        else {
            throw new TrackNotFoundException(environment.getProperty("spring.muzix.trackException"));
        }
        return  muzix;
    }
}
