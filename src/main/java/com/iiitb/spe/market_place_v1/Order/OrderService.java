package com.iiitb.spe.market_place_v1.Order;

import com.iiitb.spe.market_place_v1.Customer.Customer;
import com.iiitb.spe.market_place_v1.Customer.CustomerService;
import com.iiitb.spe.market_place_v1.CustomerStoreSlots.Slots;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.Product.ProductService;
import com.iiitb.spe.market_place_v1.Product.ProductStore;
import com.iiitb.spe.market_place_v1.Store.Store;
import com.iiitb.spe.market_place_v1.Store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductService productService;

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

    public int newOrder(int cid,int sid,List<String> prodList,List<String> quantity,List<String> dislist)
    {
        Customer c=customerService.fetchCustomer(cid);
        Store s=storeService.fetchStoreById(sid);
        List<Product> productList= prodList.parallelStream().map(x->{return productService.fetchProductById(Integer.parseInt(x));}).collect(Collectors.toList());
        Order o=new Order();
        List<ProductStore> productStoreList=s.getProductStoreList();
        o.setCustomer(c);
        o.setStore(s);
        Date date = new Date();
        o.setDateOfOrder(date);
        o.setStatus((byte)1);
        int i=0;
        for(Product p:productList)
        {
            OrderProduct op=new OrderProduct();
            op.setQuantity(Integer.parseInt(quantity.get(i)));
            op.setDisprice(Double.parseDouble(dislist.get(i)));
            i++;
            op.setProduct(p);
            op.setOrder(o);
            o.getOrderProductList().add(op);

        }

       productStoreList.parallelStream().forEach(x->{
           AtomicInteger j= new AtomicInteger();
           productList.parallelStream().forEach(y->{
               if(x.getProduct().getPid()==y.getPid())
               {
                   x.setQuantity(x.getQuantity()-Integer.parseInt(quantity.get(j.get())));

               }
               j.getAndIncrement();
           });
       });


        storeService.updateStore(s);
       return orderRepository.save(o).getOid();


    }
}
