package com.iiitb.spe.market_place_v1.Order;

import com.iiitb.spe.market_place_v1.CustomerStoreSlots.Slots;
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustormProductFormatNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Order getProducts(int oid)
    {
       return orderRepository.fetchProducts(oid).orElse(null);
    }

    public Order fetchOrderById(int id)
    {
        return orderRepository.findById(id).orElse(null);
    }
    public void deleteOrder(Order o)
    {
        orderRepository.delete(o);
    }

    public void updateOrder(Order order, Date startTime, Date endTime, String type)
    {
        if(type.equals("delivery"))
        {
            order.setOrder_type("delivery");
            order.setSlots(null);
            order.setStatus((byte)1);
        }
        else if(type.equals("pickup"))
        {
            order.setOrder_type("pickup");
            Slots s=new Slots();
            s.setStartTime(startTime);
            s.setEndTime(endTime);
            s.setStore(order.getStore());
            s.setOrder(order);
            order.setSlots(s);
            order.setStatus((byte)1);
        }
        orderRepository.save(order);
    }

}
