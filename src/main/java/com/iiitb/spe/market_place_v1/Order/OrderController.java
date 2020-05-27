package com.iiitb.spe.market_place_v1.Order;

import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustormProductFormatNew;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class OrderController {
	
	Logger logger = LogManager.getLogger(OrderController.class);

//---aayush
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/products/{oid}")
    public List<CustormProductFormatNew> getProducts(@PathVariable("oid") int oid)
    {

        Order temp=orderService.getProducts(oid);
        if(temp==null)
        {
        	logger.warn("No Product Found oid="+oid);
            throw new NotFoundException("No Products found!!");
        }
        logger.info("Products for Order Fetched oid="+oid);;
        return temp.getOrderProductList().parallelStream().map(x->new CustormProductFormatNew(x.getProduct().getProductname(),x.getQuantity())).collect(Collectors.toList());

    }


}
