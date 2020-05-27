package com.iiitb.spe.market_place_v1.Customer;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerUnitTest {

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
    private CustomerService  customerService;


    Customer mockcustomer = new Customer(1,"krishna","chaitan","123","chaitankei","qwertyui");
    @Test
    public void addCustomer() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders
                .post("/customer")
                .content(asJsonString(mockcustomer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
    }

    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.delete("/customer/{uid}", 1) )
                .andExpect(status().isOk());
    }

    @Test
    public void getSpecificCustomer() throws Exception {
         when(customerService.fetchCustomer(1)).thenReturn(mockcustomer);
          mockMvc.perform( MockMvcRequestBuilders
                .get("/customer/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.uid").value(1));
    }

    @Test
    public void updateCustomer() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .put("/customer")
                .content(asJsonString(mockcustomer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}