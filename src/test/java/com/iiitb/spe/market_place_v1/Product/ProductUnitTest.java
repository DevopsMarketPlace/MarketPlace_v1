package com.iiitb.spe.market_place_v1.Product;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class ProductUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    Product product = new Product(2,"Genms",10.00);


    @Test
    public void getproduct() throws Exception {
        when(productService.fetchProductById(2)).thenReturn(product);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/products/{pid}", 2)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.pid").value(2));
    }

    @Test
    public void createProduct() throws Exception{
        when(productService.createNewProduct(Mockito.any(Product.class))).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/product")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void updateProduct() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .put("/product/{pid}",2)
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteProduct() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders.delete("/product/{pid}", 2) )
                .andExpect(status().isOk());
    }
}