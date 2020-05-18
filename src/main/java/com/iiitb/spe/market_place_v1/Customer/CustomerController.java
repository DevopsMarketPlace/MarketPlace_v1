package com.iiitb.spe.market_place_v1.Customer;

<<<<<<< HEAD
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
=======
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DefaultEditorKit;
import java.util.Optional;
>>>>>>> cc728399fe099b69fdcf1333f42860a538faea55

@RestController
public class CustomerController {
   @Autowired
    private  CustomerService customerService;

<<<<<<< HEAD
   // Add a new customer to repository -- ok Tested
   @PostMapping("/customer")// ok tested
    public String addCustomer(@RequestBody Customer customer){
       //checking username avaliability
       Customer check = customerService.fetchbyUsername(customer.getUsername());
       if(check== null) {
           Customer newCustomer = customerService.addCustomer(customer);
            return "Successfully added";
       }
       return "Username already taken! try another one";
   }
   // Delete the record of the existing customer -- ok Tested
=======
   @PostMapping("/customer")
    public int addCustomer(@RequestBody Customer customer){
       Customer newCustomer = customerService.addCustomer(customer);
       return  newCustomer.getUid();
   }

>>>>>>> cc728399fe099b69fdcf1333f42860a538faea55
   @DeleteMapping("/customer")
    public String deleteCustomer(@RequestParam("uid") int uid){
       Customer getCustomer = customerService.fetchCustomer(uid);

       if(getCustomer== null){
<<<<<<< HEAD
           return "No Such customer Entry found to delete";
=======
           return "No Such customer Entry found";
>>>>>>> cc728399fe099b69fdcf1333f42860a538faea55
       }
       customerService.deletecustomer(getCustomer);
       return "Successfully deleted";
   }

<<<<<<< HEAD
   // Get details of a particular customer -- ok tested
   @GetMapping("/customer/{uid}")
    public Customer getSpecificCustomer(@PathVariable("uid") int uid){
       Customer result = customerService.fetchCustomer(uid);
       if(result==null){
           throw new NotFoundException("Customer with the provided id doesn't exist");
=======
   @GetMapping("/customer")
    public Customer getSpecificCustomer(@RequestParam("uid") int uid){
       Customer result = customerService.fetchCustomer(uid);
       if(result==null){
           return result;
>>>>>>> cc728399fe099b69fdcf1333f42860a538faea55
       }
       return result;
   }

<<<<<<< HEAD
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



=======
>>>>>>> cc728399fe099b69fdcf1333f42860a538faea55
}
