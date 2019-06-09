package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.MuzixNotFoundException;
import com.stackroute.muzixservice.service.MuzixServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="api/v1")
@Api(value="MuzixControllerAPI",produces = MediaType.APPLICATION_JSON_VALUE)
public class MuzixController {
    private MuzixServices muzixServices;

    @Value("${spring.muzix.muzixException}")
    private String muzixException;

    @Autowired
    public MuzixController(MuzixServices muzixServices) {
        this.muzixServices = muzixServices;
    }

    //Saves muzix track
    @PostMapping("muzix")
    @ApiOperation("Save muzix tracks")
    @ApiResponses(value = {@ApiResponse(code = 200 ,message = "ok",response = Muzix.class)})
    public ResponseEntity<?> saveMuzix(@RequestBody Muzix muzix) throws MuzixAlreadyExistsException
    {
        ResponseEntity responseEntity;
        muzixServices.saveMuzixs(muzix);
        responseEntity=new ResponseEntity<Muzix>( muzix, HttpStatus.CREATED);
        return  responseEntity;
    }

    //Get all tracks
    @GetMapping("muzixs")
    @ApiOperation("Get all Muzix tracks")
    @ApiResponses(value = {@ApiResponse(code = 200 ,message = "ok",response = Muzix.class)})
    public ResponseEntity<?> getAllMuzixs() throws Exception
    {
        return  new ResponseEntity<List<Muzix>>(muzixServices.getAllMuzixs(),HttpStatus.OK);
    }


    //Get Muzix tracks with specific muzixId
    @GetMapping("muzix/{muzixId}")
    @ApiOperation("Get Muzix tracks with specific muzixId")
    @ApiResponses(value = {@ApiResponse(code = 200 ,message = "ok",response = Muzix.class)})
    public ResponseEntity<?> findById(@PathVariable("muzixId") int muzixId) throws MuzixNotFoundException
    {
        ResponseEntity responseEntity;
            Muzix muzix=muzixServices.findById(muzixId);
            if(muzix == null){
                throw new MuzixNotFoundException(muzixException);
            }
            else {
                responseEntity = new ResponseEntity<Muzix>(muzix, HttpStatus.OK);
            }
        return  responseEntity;
    }

   //Delete muzix
    @DeleteMapping("muzix/{muzixId}")
    @ApiOperation("Delete muzix")
    @ApiResponses(value = {@ApiResponse(code = 200 ,message = "ok",response = Muzix.class)})
    public ResponseEntity<?> deleteById(@PathVariable("muzixId") int muzixId) throws MuzixNotFoundException
    {
        ResponseEntity responseEntity;
        responseEntity=new ResponseEntity<Muzix>(muzixServices.deleteById(muzixId),HttpStatus.OK);
        return  responseEntity;
    }

    //Update muzix
    @RequestMapping(method=RequestMethod.PUT, value="muzixUpdate")
    @ApiOperation("Update Muzix tracks")
    @ApiResponses(value = {@ApiResponse(code = 200 ,message = "ok",response = Muzix.class)})
    public ResponseEntity<?> updateMuzixById(@RequestBody Muzix muzix) throws  MuzixNotFoundException
    {
        ResponseEntity responseEntity;
        muzixServices.updateMuzix( muzix);
        responseEntity=new ResponseEntity<Muzix>(muzix,HttpStatus.OK);
        return  responseEntity;
    }
}
