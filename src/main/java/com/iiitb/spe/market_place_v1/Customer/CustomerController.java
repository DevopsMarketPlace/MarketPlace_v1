package com.iiitb.spe.market_place_v1.Customer;

import com.iiitb.spe.market_place_v1.Exceptions.FoundException;
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.StoreManager.StoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomerController {
   @Autowired
    private  CustomerService customerService;

   // Add a new customer to repository -- ok Tested
   @PostMapping("/customer")// ok tested
    public void  addCustomer(@RequestBody Customer customer) {
       //checking username avaliability
       Customer check = customerService.fetchbyUsername(customer.getUsername());
       if (check == null) {
           Customer newCustomer = customerService.addCustomer(customer);

       } else {
           throw new FoundException("Username already taken");
       }
   }
   // Delete the record of the existing customer -- ok Tested
   @DeleteMapping("/customer")
    public String deleteCustomer(@RequestParam("uid") int uid){
       Customer getCustomer = customerService.fetchCustomer(uid);

       if(getCustomer== null){
           return "No Such customer Entry found to delete";
       }
       customerService.deletecustomer(getCustomer);
       return "Successfully deleted";
   }

   // Get details of a particular customer -- ok tested
   @GetMapping("/customer/{uid}")
    public Customer getSpecificCustomer(@PathVariable("uid") int uid){
       Customer result = customerService.fetchCustomer(uid);
       if(result==null){
           throw new NotFoundException("Customer with the provided id doesn't exist");
       }
       return result;
   }

   // Get complete list of customers ( Only for testing purpose) -- Not Required -- ok tested
   @GetMapping("/customers")
    public List<Customer> getCustomers(){
       return customerService.getallCustomers();
   }

   // Updating customer details
   @PutMapping("/customer")
    public String updateCustomer(@RequestBody Customer customer){
       customerService.updateCustomer(customer);
       return "Successfully Updated";
   }

   // fetching all the orders of a customer (if Present)
   @GetMapping("customer/order")
    public List<Order> getOrders(@RequestParam("uid") int uid){
       Customer getCustomer = customerService.fetchCustomer(uid);
       if(getCustomer == null){
           throw  new NotFoundException("Provided customer id doesn't exist");
       }
       Customer result = customerService.fetchOrderList(uid);
       if(result == null){
           throw new NotFoundException("No orders found for username " + getCustomer.getUsername());
       }
       return result.getOrderList();
   }
    @GetMapping("/customer/login")
    public int authenticate(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        Customer check =customerService.fetchbyUsername(username);
        if(check==null)
        {
            throw new NotFoundException("username or password incorrect");
        }
        else if(!check.getPassword().equals(password))
        {
            System.out.println(check.getPassword()+" "+password);
            throw new NotFoundException("password incorrect");
        }


        return check.getUid();
    }

   






}
