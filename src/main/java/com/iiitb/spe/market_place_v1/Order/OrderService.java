package com.iiitb.spe.market_place_v1.Order;

import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustormProductFormatNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
