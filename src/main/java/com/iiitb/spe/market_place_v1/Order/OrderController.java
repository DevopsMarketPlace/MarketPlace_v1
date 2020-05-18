package com.iiitb.spe.market_place_v1.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController{

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, value = "/order")
    public void MakeOrder(@RequestBody Demo demo){
        orderService.makeorder(demo);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/order/{oid}")
    public void DeleteOrder(@PathVariable int oid){
        orderService.deleteOrder(oid);
    }
}

