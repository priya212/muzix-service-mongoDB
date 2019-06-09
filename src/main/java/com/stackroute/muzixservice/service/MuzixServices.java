package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.MuzixNotFoundException;

import java.util.List;

public interface MuzixServices {
    public Muzix saveMuzixs(Muzix muzix) throws MuzixAlreadyExistsException;
    public List<Muzix> getAllMuzixs() throws Exception;
    public Muzix findById(int muzixId) throws MuzixNotFoundException;
    public Muzix deleteById(int muzixId) throws MuzixNotFoundException;
    public Muzix updateMuzix(Muzix muzix) throws MuzixNotFoundException;
}
