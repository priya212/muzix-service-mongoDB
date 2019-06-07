package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
/*@DataJpaTest*/
@DataMongoTest
public class MuzixRepositoryTest {

    @Autowired
    private MuzixRepository muzixRepository;

    private Muzix muzix;

    @Before
    public void setup()
    {
        muzix=new Muzix();
        muzix.setTrackId(1);
        muzix.setTrackName("Teri meri yariyaa");
        muzix.setComments("Classmates track");

    }

    @After
    public void tearDown()
    {
        muzixRepository.deleteAll();
    }

    @Test
    public void testSavedMuzix()
    {
        muzixRepository.save(muzix);
        Muzix fetchMuzix=muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertEquals(1,fetchMuzix.getTrackId());
    }
    @Test
    public void testSaveMuzixFailure(){
        Muzix testMuzix = new Muzix(2,"coca cola","luka chuppi track");
        muzixRepository.save(muzix);
        Muzix fetchMuzix = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertNotSame(testMuzix,muzix);
    }

    @Test
    public void testGetAllMuzix()
    {
        Muzix muzix1=new Muzix(2,"Deva Tuzya","Duniyadari track");

        muzixRepository.save(muzix);
        muzixRepository.save(muzix1);

        List<Muzix> muzixList=muzixRepository.findAll();
        List<Muzix> expectedList=new ArrayList<>();
        expectedList.add(muzix);
        expectedList.add(muzix1);
        Assert.assertEquals(expectedList,muzixList);
    }

    @Test
    public void testDelete()
    {
        Muzix muzix1=new Muzix(2,"Deva Tuzya","Duniyadari track");
        muzixRepository.save(muzix);
        muzixRepository.save(muzix1);

        muzixRepository.deleteById(1);

        List<Muzix> muzixList=muzixRepository.findAll();
        Assert.assertEquals(2,muzixList.get(0).getTrackId());

    }

    @Test
    public void testDeleteFailure()
    {
        Muzix muzix1=new Muzix(2,"Deva Tuzya","Duniyadari track");
        muzixRepository.save(muzix);
        muzixRepository.save(muzix1);

        muzixRepository.deleteById(1);

        List<Muzix> muzixList=muzixRepository.findAll();
        Assert.assertNotEquals(1,muzixList.get(0).getTrackId());

    }

    /*@Test
    public void testFindByName() throws TrackNotFoundException
    {
        muzixRepository.save(muzix);
        Muzix muzix1=muzixRepository.findByName("Teri meri yariyaa");
        Assert.assertEquals(muzix,muzix1);

    }*/


 /*   @Test
    public void testFindByNameFailure() throws TrackNotFoundException
    {
        muzixRepository.save(muzix);
        Muzix muzix2=new Muzix(4,"sorry sorry","ABCD track");
        Muzix muzix1=muzixRepository.findByName("Teri meri yariyaa");
        Assert.assertNotEquals(muzix2,muzix1);

    }*/

}
