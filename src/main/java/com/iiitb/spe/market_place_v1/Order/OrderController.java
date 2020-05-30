package com.iiitb.spe.market_place_v1.Order;

import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustomProductFormat;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustomStoreFormat;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustormProductFormatNew;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
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
    public List<CustomProductFormat> getProducts(@PathVariable("oid") int oid)
    {

        Order temp=orderService.getProducts(oid);
        if(temp==null)
        {
        	logger.warn("No Product Found oid="+oid);
            throw new NotFoundException("No Products found!!");
        }
        logger.info("Products for Order Fetched oid="+oid);;
        return temp.getOrderProductList().parallelStream().map(x->new CustomProductFormat(x.getProduct().getPid(),x.getProduct().getProductname(),x.getProduct().getPprice(),x.getDisprice(),x.getQuantity())).collect(Collectors.toList());

    }

    @DeleteMapping("/order/{oid}")
    public void removeOrder(@PathVariable("oid") int oid)
    {
        Order existingOrder=orderService.fetchOrderById(oid);
        if(existingOrder==null)
        {
            throw new NotFoundException("No Order found!!");
        }
        orderService.deleteOrder(existingOrder);
    }

    @PutMapping("/order/{oid}")
    public void updateOrder(@PathVariable("oid") int oid, @RequestParam("type") String type, @RequestParam("startTime")  @DateTimeFormat(pattern = "HH:mm") Date startTime, @RequestParam("endTime")  @DateTimeFormat(pattern = "HH:mm") Date endTime)
    {
        Order existingOrder=orderService.fetchOrderById(oid);
        if(existingOrder==null)
        {
            throw new NotFoundException("No Order found!!");
        }
        orderService.updateOrder(existingOrder,startTime,endTime,type);
    }

    @PostMapping("/order")
    public int createOrder(@RequestParam("cid") int cid,@RequestParam("sid") int sid,@RequestParam("prodlist") String prodList,@RequestParam("quantity") String quantity,@RequestParam("dislist") String dislist)
    {
        String[] prod_temp=prodList.split("_");
        String[] quantity_temp=quantity.split("_");
        String[] dislist_temp=dislist.split("_");

        return orderService.newOrder(cid,sid, Arrays.asList(prod_temp),Arrays.asList(quantity_temp), Arrays.asList(dislist_temp));
    }

}
