package com.stackroute.muzixservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.MuzixExceptionController;
import com.stackroute.muzixservice.exception.MuzixNotFoundException;
import com.stackroute.muzixservice.service.MuzixServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MuzixControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Muzix muzix;

    @MockBean
    private MuzixServices muzixServices;

    @InjectMocks
    private MuzixController muzixController;

    private List<Muzix> muzixList =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixController).setControllerAdvice(new MuzixExceptionController()).build();
        muzix=new Muzix();
        muzix.setMuzixId(1);
        muzix.setMuzixName("Teri meri yariyaa");
        muzix.setComments("Classmates track");
        muzixList = new ArrayList();
        muzixList.add(muzix);
    }


    @Test
    public void saveMuzix() throws Exception {
        when(muzixServices.saveMuzixs(any())).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveMuzixFailure() throws Exception {
        when(muzixServices.saveMuzixs(any())).thenThrow(MuzixAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                 .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllMuzixTest() throws Exception {
        when(muzixServices.getAllMuzixs()).thenReturn(muzixList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzixs")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findByIdTest() throws Exception{
        when(muzixServices.findById(1)).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findByIdTestFailure() throws Exception{
        when(muzixServices.findById(3)).thenThrow(MuzixNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix/5")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMuzixTest() throws Exception{
        when(muzixServices.deleteById(1)).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/muzix/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMuzixTestFailure() throws Exception{
        when(muzixServices.deleteById(9)).thenThrow(MuzixNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/muzix/9")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateMuzixTest() throws Exception{
        Muzix muzix1=new Muzix(1,"coca cola","Luka chuppi track");
        when(muzixServices.updateMuzix(muzix1)).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/muzixUpdate")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateMuzixTestFailure() throws Exception{
        Muzix muzix1=new Muzix(1,"coca cola","Luka chuppi track");
        when(muzixServices.updateMuzix(muzix)).thenThrow(MuzixNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/muzixUpdate")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
