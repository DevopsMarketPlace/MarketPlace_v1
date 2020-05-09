package com.iiitb.spe.market_place_v1.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepo repo;
    @GetMapping("/Customer/{uid}")
    public Optional<Customer> getCustomer(@PathVariable int uid)
    {
        return repo.findById(uid);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Customer")
    public String addcustomer(@RequestBody Customer customer){
        repo.save(customer);
        return "Registered Successfully";
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/Customer/{uid}")
    public String updatecustomer(@RequestBody Customer customer, @PathVariable int uid){
        repo.save(customer);
        return "Updated Successfully";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Customer/{uid}")
    public String deletecustomer(@RequestBody Customer customer, @PathVariable int uid){
          repo.deleteById(uid);
          return "Removed Successfully";
    }
}
