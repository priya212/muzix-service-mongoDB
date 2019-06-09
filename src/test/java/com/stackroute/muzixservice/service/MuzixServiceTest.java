package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.MuzixNotFoundException;
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
        muzix.setMuzixId(1);
        muzix.setMuzixName("Teri meri yariyaa");
        muzix.setComments("Classmates track");

        muzixList=new ArrayList<>();
        muzixList.add(muzix);
        optional= Optional.of(muzix);
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
        when(muzixRepository.save(any())).thenReturn(null);
        Muzix savedMuzix = muzixServices.saveMuzixs(muzix);
        System.out.println("savedMuzix " + savedMuzix);
        Assert.assertEquals(muzix,savedMuzix);
    }

    @Test
    public void findByIdTest() throws MuzixNotFoundException
    {
        muzixRepository.save(muzix);
        Muzix muzix1=null;
        when(muzixRepository.findById(1)).thenReturn(optional);
        Muzix muzix2=muzixServices.findById(1);
        Assert.assertEquals(muzix1,muzix2);
    }

 /*   @Test(expected = MuzixNotFoundException.class)
    public void findByIdTestFailure() throws MuzixNotFoundException
    {
        muzixRepository.save(muzix);
        Muzix muzix1=null;
        when(muzixRepository.findById(6)).thenReturn(null);
        Muzix muzix2=muzixServices.findById(6);
        Assert.assertEquals(muzix1,muzix2);
    }*/

    @Test
    public void getAllMuzixTest() throws Exception {
        muzixRepository.save(muzix);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(muzixList);
        List<Muzix> muzixList1 = muzixServices.getAllMuzixs();
        Assert.assertEquals(muzixList,muzixList1);
    }
    @Test(expected = Exception.class)
    public void getAllMuzixTestFailure() throws Exception {

        muzixRepository.save(muzix);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(null);
        List<Muzix> muzixList1 = muzixServices.getAllMuzixs();
        Assert.assertNotEquals(muzixList,muzixList1);
    }

    @Test
    public void deleteTrackTest() throws Exception {
        when(muzixRepository.findById(1)).thenReturn(optional);
        muzixServices.deleteById(muzix.getMuzixId());
        List<Muzix> muzixList1 = muzixServices.getAllMuzixs();
        Assert.assertEquals(false,muzixList1.contains(muzix));
    }
/*
    *//*@Test(expected = MuzixNotFoundException.class)
    public void deleteTrackTestFailure() throws MuzixNotFoundException{
        when(muzixRepository.findById(1)).thenReturn(optional);
        muzixServices.deleteById(1);
        List<Muzix> muzixList1 = muzixServices.getAllMuzixs();
        Assert.assertEquals(false,muzixList1.contains(muzix));
     *//**//*   doThrow(new MuzixNotFoundException()).when(muzixRepository).deleteById(eq(9));
       muzixRepository.deleteById(9);*//**//*

    }*/
    @Test
    public void updateMuzixTest() throws  MuzixNotFoundException{
        Muzix muzix1=new Muzix(1,"Teri meri yariyaa","Luka chuppi track");
        when(muzixRepository.existsById(1)).thenReturn(true);
        when(muzixRepository.save(any())).thenReturn(muzix);
        Muzix muzix2=muzixServices.updateMuzix(muzix1);
        Assert.assertEquals(muzix1,muzix2);
    }
   /* @Test(expected = MuzixNotFoundException.class)
    public void updateMuzixTestFailure() throws  MuzixNotFoundException{
        Muzix muzix1=new Muzix(5,"Teri meri yariyaa","Luka chuppi track");
        when(muzixRepository.findById(5)).thenThrow(new MuzixNotFoundException());
        Muzix muzix2=muzixServices.updateMuzix(muzix1);*//*
        when(muzixRepository.save(any())).thenReturn(null);
        Assert.assertSame(muzix1,muzix2);*//*
    }*/
}
