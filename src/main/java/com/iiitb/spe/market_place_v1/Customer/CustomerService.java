package com.iiitb.spe.market_place_v1.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> cc728399fe099b69fdcf1333f42860a538faea55
@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public Customer addCustomer(Customer c){
        return customerRepo.save(c);
    }

    public Customer updateCustomer(Customer c){
<<<<<<< HEAD

        return customerRepo.save(c);
    }
    public Customer fetchCustomer(int id){ return customerRepo.findById(id).orElse(null); }

    public void deletecustomer(Customer existingCustomer){customerRepo.delete(existingCustomer); }

    public Customer fetchOrderList(int id){ return customerRepo.fetchOrders(id).orElse(null); }

    public Customer fetchbyUsername(String uname){ return customerRepo.findByUsername(uname).orElse(null); }

    public List<Customer> getallCustomers(){
        List<Customer> result = new ArrayList<Customer>();
        customerRepo.findAll().forEach(result::add);
        return result;
    }

=======
        return customerRepo.save(c);
    }
    public Customer fetchCustomer(int id){
        return customerRepo.findById(id).orElse(null);
    }
    public void deletecustomer(Customer existingCustomer){
        customerRepo.delete(existingCustomer);
    }
    public Customer fetchOrderList(int id){
        return customerRepo.fetchOrders(id);
    }
>>>>>>> cc728399fe099b69fdcf1333f42860a538faea55
}
