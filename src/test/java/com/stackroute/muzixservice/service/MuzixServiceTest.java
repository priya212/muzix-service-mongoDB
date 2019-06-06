package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MuzixServiceTest {

    private Muzix muzix;

    @Mock
    private MuzixRepository muzixRepository;

    Optional optional;

    @InjectMocks
    private MuzixServicesImpl muzixServices;

    List<Muzix> muzixList=null;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        muzix=new Muzix();
        muzix.setTrackId(1);
        muzix.setTrackName("Teri meri yariyaa");
        muzix.setComments("Classmates track");

        muzixList=new ArrayList<>();
        muzixList.add(muzix);
        optional= Optional.of(muzix);
    }

    @After
    public void tearDown()
    {
        muzixRepository.deleteAll();
    }


    @Test
    public void saveMuzixTestSuccess() throws MuzixAlreadyExistsException
    {
        when(muzixRepository.save(any())).thenReturn(muzix);
        Muzix saveMuzix=muzixServices.saveMuzixs(muzix);
        Assert.assertEquals(muzix,saveMuzix);

        verify(muzixRepository,times(1)).save(muzix);
    }


    @Test(expected = MuzixAlreadyExistsException.class)
    public void saveMuzixTestFailure() throws MuzixAlreadyExistsException {
        when(muzixRepository.save((Muzix)any())).thenReturn(null);
        Muzix savedMuzix = muzixServices.saveMuzixs(muzix);
        System.out.println("savedMuzix " + savedMuzix);
        Assert.assertEquals(muzix,savedMuzix);
    }

    @Test
    public void getAllMuzixTest(){
        muzixRepository.save(muzix);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(muzixList);
        List<Muzix> muzixList1 = muzixServices.getAllMuzixs();
        Assert.assertEquals(muzixList,muzixList1);
    }

    @Test
    public void getAllMuzixTestFailure(){

        muzixRepository.save(muzix);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(null);
        List<Muzix> muzixList1 = muzixServices.getAllMuzixs();
        Assert.assertNotEquals(muzixList,muzixList1);
    }

    @Test
    public void findByIdTest() throws TrackNotFoundException
    {
        muzixRepository.save(muzix);
        Muzix muzix1=null;
        when(muzixRepository.getOne(1)).thenReturn(muzix1);
        Muzix muzix2=muzixServices.findById(1);
        Assert.assertEquals(muzix1,muzix2);
    }

    @Test
    public void findByNameTest()  throws TrackNotFoundException
    {
        Muzix muzix1=new Muzix(1,"Teri meri yariyaa","Classmates track");
        muzixRepository.save(muzix);
        when(muzixRepository.save(any())).thenReturn(muzix);
        when(muzixRepository.findByName("Teri meri yariyaa")).thenReturn(muzix);
        Muzix muzix2=muzixServices.findByName("Teri meri yariyaa");
        Assert.assertEquals(muzix1,muzix2);
    }

    @Test()
    public void deleteTrackTest() throws TrackNotFoundException {
        when(muzixRepository.findById(1)).thenReturn(optional);
        muzixServices.deleteById(muzix.getTrackId());
        List<Muzix> muzixList1 = muzixServices.getAllMuzixs();
        Assert.assertEquals(false,muzixList1.contains(muzix));
    }

    @Test()
    public void deleteTrackTestFailure() throws TrackNotFoundException {
        when(muzixRepository.findById(1)).thenReturn(optional);
        muzixServices.deleteById(muzix.getTrackId());
        List<Muzix> muzixList1 = muzixServices.getAllMuzixs();
        Assert.assertNotSame(true,muzixList1.contains(muzix));
    }

    @Test
    public void updateMuzixTest() throws  TrackNotFoundException{
        Muzix muzix1=new Muzix(1,"Teri meri yariyaa","Luka chuppi track");
        when(muzixRepository.getOne(1)).thenReturn(muzix);
        when(muzixRepository.existsById(1)).thenReturn(true);
        when(muzixRepository.save(any())).thenReturn(muzix);
        Muzix muzix2=muzixServices.updateMuzix(muzix1);
        Assert.assertEquals(muzix1,muzix2);
    }

    @Test
    public void updateMuzixTestFailure() throws  TrackNotFoundException{
        Muzix muzix1=new Muzix(1,"Teri meri yariyaa","Luka chuppi track");
        when(muzixRepository.getOne(1)).thenReturn(muzix);
        when(muzixRepository.existsById(1)).thenReturn(true);
        when(muzixRepository.save(any())).thenReturn(muzix);
        Muzix muzix2=muzixServices.updateMuzix(muzix1);
        Assert.assertNotSame(muzix1,muzix2);
    }
}
