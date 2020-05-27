package com.iiitb.spe.market_place_v1.Customer;

import com.iiitb.spe.market_place_v1.Exceptions.FoundException;
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.StoreManager.StoreManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomerController {
	
	Logger logger = LogManager.getLogger(CustomerController.class);
	
   @Autowired
    private  CustomerService customerService;

   // Add a new customer to repository -- ok Tested
   @PostMapping("/customer")// ok tested
    public void  addCustomer(@RequestBody Customer customer) {
       //checking username avaliability
       Customer check = customerService.fetchbyUsername(customer.getUsername());
       if (check == null) {
    	   logger.info("New Customer Added "+customer.getUsername());
           Customer newCustomer = customerService.addCustomer(customer);

       } else {
    	   logger.warn("Username Already Taken");
           throw new FoundException("Username already taken");
       }
   }
   // Delete the record of the existing customer -- ok Tested
   @DeleteMapping("/customer/{uid}")
    public String deleteCustomer(@PathVariable("uid") int uid){
       Customer getCustomer = customerService.fetchCustomer(uid);

       if(getCustomer== null){
    	   logger.warn("No Such customer Entry found to delete");
           return "No Such customer Entry found to delete";
       }
       customerService.deletecustomer(getCustomer);
	   logger.info("Customer Successfully deleted uid="+uid);
       return "Successfully deleted";
   }

   // Get details of a particular customer -- ok tested
   @GetMapping("/customer/{uid}")
    public Customer getSpecificCustomer(@PathVariable("uid") int uid){
       Customer result = customerService.fetchCustomer(uid);
       if(result==null){
    	   logger.warn("Customer with the provided id doesn't exist");
           throw new NotFoundException("Customer with the provided id doesn't exist");
       }
	   logger.info("Customer Fetched Uid="+uid);
       return result;
   }

   // Get complete list of customers ( Only for testing purpose) -- Not Required -- ok tested
   @GetMapping("/customers")
    public List<Customer> getCustomers(){
	   logger.info("All Customer fetched");
       return customerService.getallCustomers();
   }

   // Updating customer details
   @PutMapping("/customer")
    public String updateCustomer(@RequestBody Customer customer){
       customerService.updateCustomer(customer);
	   logger.info("Customer Updated");
       return "Successfully Updated";
   }

   // fetching all the orders of a customer (if Present)
   @GetMapping("customer/order")
    public List<Order> getOrders(@RequestParam("uid") int uid){
       Customer getCustomer = customerService.fetchCustomer(uid);
       if(getCustomer == null){
    	   logger.warn("Provided customer id doesn't exist");
           throw  new NotFoundException("Provided customer id doesn't exist");
       }
       Customer result = customerService.fetchOrderList(uid);
       if(result == null){
    	   logger.warn("No orders found for username " + getCustomer.getUsername());
           throw new NotFoundException("No orders found for username " + getCustomer.getUsername());
       }
	   logger.info("Orders of Customer Fetched Uid="+uid);
       return result.getOrderList();
   }
    @GetMapping("/customer/login")
    public int authenticate(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        Customer check =customerService.fetchbyUsername(username);
        if(check==null)
        {
        	 logger.warn("username or password incorrect");
            throw new NotFoundException("username or password incorrect");
        }
        else if(!check.getPassword().equals(password))
        {
            System.out.println(check.getPassword()+" "+password);
            logger.warn("password incorrect");
            throw new NotFoundException("password incorrect");
        }

 	   logger.info("Customer Logged Id Uid="+check.getUid());
        return check.getUid();
    }

   






}
