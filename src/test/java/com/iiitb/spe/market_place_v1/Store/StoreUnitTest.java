package com.iiitb.spe.market_place_v1.Store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiitb.spe.market_place_v1.StoreManager.StoreManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StoreController.class)
public class StoreUnitTest {

    @Autowired
    private MockMvc mockMvc;
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @MockBean
    private StoreService storeService;
    Date start = new Date();
    Date end = new Date();

    Store store = new Store(1,"SuperMarket",start,end,5,10,new StoreManager());
    @Test
    public void createStore() throws Exception {
        when(storeService.createNewStore(Mockito.any(Store.class),Mockito.any(StoreManager.class))).thenReturn(store);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/store/{mgr_id}",101)
                .content(asJsonString(store))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateStore() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/store/{mgr_id}",101)
                .content(asJsonString(store))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteStore() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.delete("/store/{sid}", 1) )
                .andExpect(status().isNotFound());
    }


    @Test
    public void getSpecificStore() throws Exception {
        when(storeService.fetchStoreById(1)).thenReturn(store);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/store/{sid}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}