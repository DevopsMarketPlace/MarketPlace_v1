package com.iiitb.spe.market_place_v1.Order;


import com.iiitb.spe.market_place_v1.Customer.Customer;
import com.iiitb.spe.market_place_v1.Customer.CustomerService;
import com.iiitb.spe.market_place_v1.Store.Store;
import com.iiitb.spe.market_place_v1.Store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StoreService storeService;

    @Autowired
    CustomerService customerService;

    private List<Order> orders = new ArrayList<>();

    //@Autowired
    //StoreService storeService;

    public void makeorder(Demo demo){
        demo.getProductList();
        Order order = new Order();
        order.setDateOfOrder(java.time.LocalDateTime.now());
        order.setStatus((byte) 1);
        Store store = storeService.fetchStoreById(demo.getSid());
        order.setStore(store);
        Customer customer = customerService.fetchCustomer(demo.getCid());
        order.setCustomer(customer);
        orderRepository.save(order);
    }


    public void deleteOrder(int oid) {
        orderRepository.deleteById(oid);
    }
}
