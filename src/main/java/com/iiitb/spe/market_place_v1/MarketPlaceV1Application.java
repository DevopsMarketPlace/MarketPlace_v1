package com.iiitb.spe.market_place_v1;

import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.Product.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

@SpringBootApplication
public class MarketPlaceV1Application extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepo productRepo;
    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
        return builder.sources(MarketPlaceV1Application.class);
    }

    public static void main (String[] args) {
        SpringApplication.run(MarketPlaceV1Application.class, args);
    }

    @Controller
    class MyController {

        @RequestMapping("/")
        public String handler () {
            return "Welcome.html";
        }
    }

    @Override
    public void run(String... arg0) throws Exception {

        for(int i=0;i<30;i++)
        {
            Random r=new Random();
            Product p=new Product();
            p.setProductname("Product"+i);
            p.setPprice(r.nextInt(100)+5);
            productRepo.save(p);
        }
    }
}
