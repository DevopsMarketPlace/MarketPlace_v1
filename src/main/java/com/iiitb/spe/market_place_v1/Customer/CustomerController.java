package com.iiitb.spe.market_place_v1.Customer;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DefaultEditorKit;
import java.util.Optional;

@RestController
public class CustomerController {
   @Autowired
    private  CustomerService customerService;

   @PostMapping("/customer")
    public int addCustomer(@RequestBody Customer customer){
       Customer newCustomer = customerService.addCustomer(customer);
       return  newCustomer.getUid();
   }

   @DeleteMapping("/customer")
    public String deleteCustomer(@RequestParam("uid") int uid){
       Customer getCustomer = customerService.fetchCustomer(uid);

       if(getCustomer== null){
           return "No Such customer Entry found";
       }
       customerService.deletecustomer(getCustomer);
       return "Successfully deleted";
   }

   @GetMapping("/customer")
    public Customer getSpecificCustomer(@RequestParam("uid") int uid){
       Customer result = customerService.fetchCustomer(uid);
       if(result==null){
           return result;
       }
       return result;
   }

}
