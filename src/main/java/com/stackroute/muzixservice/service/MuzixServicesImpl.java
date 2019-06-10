package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.MuzixNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "muzix")
@Service
@PropertySource("classpath:application.properties")
public class MuzixServicesImpl implements com.stackroute.muzixservice.service.MuzixServices {
    private MuzixRepository muzixRepository;

    @Autowired
    public MuzixServicesImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }


    @Value("spring.muzix.muzixException")
    private String notFoundException;

    @Value("${spring.muzix.alreadyExistException}")
   private String muzixExistException;

    // Saves muzixs
    @Override
    @CacheEvict(allEntries = true)
    public Muzix saveMuzixs(Muzix muzix) throws MuzixAlreadyExistsException {
      if(muzixRepository.existsById(muzix.getMuzixId())) {
          throw new MuzixAlreadyExistsException(muzixExistException);
      }
      Muzix savedMuzix=muzixRepository.save(muzix);
      if(savedMuzix == null) {
          throw new MuzixAlreadyExistsException(muzixExistException);
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

    //Get all muzixs
    @Override
    @Cacheable("muzix")
    public List<Muzix> getAllMuzixs() throws  Exception{
        simulateDelay();
        List<Muzix> muzixList=muzixRepository.findAll();
        if(muzixList.isEmpty())
        {
            throw  new Exception("No muzix available");
        }
        return  muzixList;
    }

    //Find muzix by ID
    @Override
    public Muzix findById(int muzixId) throws MuzixNotFoundException {
        Muzix muzix =null;
        if(muzixRepository.existsById(muzixId)) {
            Optional optional=muzixRepository.findById(muzixId);
                muzix=(Muzix) optional.get();
        }
        else {
            throw new MuzixNotFoundException(notFoundException);
        }
        return muzix;
    }

    //Delete muzix
    @Override
    @CacheEvict(allEntries = true)
    public Muzix deleteById(int muzixId) throws MuzixNotFoundException {
       Optional optional=muzixRepository.findById(muzixId);
       Muzix muzix=null;
       if(optional.isPresent()) {
           muzix=(Muzix) optional.get();
           muzixRepository.deleteById(muzixId);
       }
       else {
           throw new MuzixNotFoundException(notFoundException);
       }
       return  muzix;
    }

    //Update muzix
    @Override
    @CacheEvict(allEntries = true)
    public Muzix updateMuzix(Muzix muzix) throws MuzixNotFoundException{
        Optional optional=muzixRepository.findById(muzix.getMuzixId());
        if(optional.isPresent()){
            muzix.setComments(muzix.getComments());
            muzixRepository.save(muzix);
        }
        else {
            throw new MuzixNotFoundException(notFoundException);
        }
        return  muzix;
    }
}