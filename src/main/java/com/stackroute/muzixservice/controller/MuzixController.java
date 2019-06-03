package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.MuzixServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class MuzixController {
    private MuzixServices muzixServices;

    public MuzixController(MuzixServices muzixServices) {
        this.muzixServices = muzixServices;
    }

    @PostMapping("Muzix")
    public ResponseEntity<?> saveUser(@RequestBody Muzix muzix) throws MuzixAlreadyExistsException
    {
        ResponseEntity responseEntity;
        muzixServices.saveMuzixs(muzix);
        responseEntity=new ResponseEntity<String>("Successfully created ", HttpStatus.CREATED);
        return  responseEntity;
    }

    @GetMapping("Muzix")
    public ResponseEntity<?> getAllMuzixs()
    {
        return  new ResponseEntity<List<Muzix>>(muzixServices.getAllMuzixs(),HttpStatus.OK);
    }


    @GetMapping("MuzixTrackById/{trackId}")
    public ResponseEntity<?> findById(@PathVariable("trackId") int trackId) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;
       // try {
            Muzix muzix=muzixServices.findById(trackId);
            if(muzix == null){
                //responseEntity=new ResponseEntity<String>("Track Not found",HttpStatus.OK);
                throw new TrackNotFoundException("Track not found");
            }
            else {
                responseEntity = new ResponseEntity<String>("Track found", HttpStatus.OK);
            }
        /*}
        catch(TrackNotFoundException ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }*/
        return  responseEntity;
    }

    @GetMapping("MuzixTrackByName/{trackName}")
    public ResponseEntity<?> findByName(@PathVariable("trackName") String trackName) throws TrackNotFoundException{
        ResponseEntity responseEntity=null;
            Muzix muzix=muzixServices.findByName(trackName);
            if(muzix != null){
                responseEntity = new ResponseEntity<String>("Track found", HttpStatus.OK);
            }
        return responseEntity;
    }

    @GetMapping("MuzixDelete/{trackId}")
    public ResponseEntity<?> deleteById(@PathVariable("trackId") int trackId) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;
        muzixServices.deleteById(trackId);
        responseEntity=new ResponseEntity<String>("Track Deleted",HttpStatus.OK);
        return  responseEntity;
    }
    @RequestMapping(method=RequestMethod.PUT, value="MuzixUpdate/{trackId}")
    public ResponseEntity<?> updateMuzixById(@PathVariable int trackId,@RequestBody Muzix muzix) throws  TrackNotFoundException
    {
        ResponseEntity responseEntity;
        muzixServices.updateMuzixById(trackId, muzix);
        responseEntity=new ResponseEntity<String>("Track Updated",HttpStatus.OK);
        return  responseEntity;
    }

}
