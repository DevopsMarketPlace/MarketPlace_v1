package com.iiitb.spe.market_place_v1.StoreManager;

import com.iiitb.spe.market_place_v1.Exceptions.FoundException;
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.Product.ProductStore;
import com.iiitb.spe.market_place_v1.Store.Store;
import com.iiitb.spe.market_place_v1.Store.StoreService;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustomStoreFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class StoreManagerController {
	
	Logger logger = LogManager.getLogger(StoreManagerController.class);

    @Autowired
    private StoreManagerService storeManagerService;

    @Autowired
    private StoreService storeService;

    @GetMapping("/storemanager/login")
    public StoreManager authenticate(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        StoreManager existingManager=storeManagerService.fetchByUsername(username);
        if(existingManager==null)
        {
        	logger.warn("username or password incorrect");
            throw new NotFoundException("username or password incorrect");
        }
        if(!password.equals(existingManager.getPassword()))
        {
        	logger.warn("password incorrect");
            throw new NotFoundException("password incorrect");
        }
        logger.info("Authentication Successfull");
        return existingManager;
    }
    @PostMapping("/storemanager")
    public void createStoreManager(@RequestBody StoreManager storeManager)
    {
        StoreManager existingManager=storeManagerService.fetchByUsername(storeManager.getUsername());
        if(existingManager!=null)
        {
        	logger.warn("username already taken!! try different");
            throw new FoundException("username already taken!! try different");
        }
        logger.info("Store Manager Created "+storeManager.getFirstname());
        storeManagerService.createStoreManager(storeManager);

    }

    @GetMapping("/storemanager/stores/{mgr_id}")
    public List<Store> getAllStores(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        if(existingManager==null)
        {
        	logger.warn("Store Manager not found Mgr_id="+mgr_id);
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
        	logger.warn("No Stores Found");
            throw new FoundException("No Stores found");
        }
        logger.info("Fetched All Stores");
        return temp.getStoreList();

    }
    @GetMapping("/storemanager/stores/{mgr_id}/count")
    public int getStoreCount(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        if(existingManager==null)
        {
        	logger.warn("Store Manager not found");
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
            return 0;
        }
        logger.info("Count of Stores Fetched");
        return temp.getStoreList().size();
    }

    @GetMapping("/storemanager/stores/{mgr_id}/totalordercount")
    public int getTotalOrderCount(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        if(existingManager==null)
        {
        	logger.warn("Store Manager Not Found Mgr_id="+mgr_id);
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
        	logger.warn("Store Not Found");
            return 0;
        }


       List<Integer> countList= temp.getStoreList().parallelStream().map(x->{

             Store s=storeService.fetchOrderList(x.getSid());
             if(s==null)
                 return 0;
             return s.getOrderList().size();

         }).collect(Collectors.toList());
       logger.info("Total Order Count Fetched");
       return  countList.parallelStream().mapToInt(Integer::valueOf).sum();
    }

    @GetMapping("/storemanager/stores/{mgr_id}/ordercount/type")
    public List<Integer> getTotalOrderCountByType(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        List<Integer> result=new ArrayList<Integer>();
        if(existingManager==null)
        {
        	logger.warn("Store Manager Not Found Mgr_id="+mgr_id);
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
            result.add(0);
            return result;
        }
        int a=0;
        int b=0;
    for (Store s: temp.getStoreList()) {
        Store temp1 = storeService.fetchOrderListByType("delivery", s.getSid());
        if (temp1 == null) {
            a += 0;
        } else {
            a += temp1.getOrderList().size();

        }


        temp1 = storeService.fetchOrderListByType("pickup", s.getSid());
        if (temp1 == null) {
            b += 0;
        } else {
            b += temp1.getOrderList().size();

        }
    }
        logger.info("Total Order by Type Fetched");
        result.add(a);
        result.add(b);
        return result ;
    }
    @GetMapping("/storemanager/stores/inventory/{mgr_id}")
    public List<CustomStoreFormat> getInventoryAlerts(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        if(existingManager==null)
        {
        	logger.warn("");
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null) {
            return null;
        }

        List<Store> storeList=temp.getStoreList();
        List<CustomStoreFormat> response=new ArrayList<CustomStoreFormat>();
        if(storeList==null)
            return null;
        for(Store s:storeList)
        {
            //s.getProductStoreList().parallelStream().map(y->new CustomStoreFormat(s.getSid(),s.getName(),y.getProduct().getProductname(),y.getQuantity())).collect(Collectors.toList());
            Store temp1=storeService.fetchProductStoreListByQuantity(s.getSid(),10);
            if(temp1==null)
            {
            }
            else{
                List<ProductStore> psList=temp1.getProductStoreList();

                    response.addAll(psList.parallelStream().filter(x->x.getQuantity()<=10).map(y->new CustomStoreFormat(s.getSid(),s.getName(),y.getProduct().getProductname(),y.getQuantity())).collect(Collectors.toList()));

            }


        }

        logger.info("Inventory Alert Fetched");
        return  response;
    }

    @GetMapping("/storemanager/{mgr_id}/store/{sid}/orders/{type}")
    public List<Order> getOrderByType(@PathVariable("mgr_id") int mgr_id,@PathVariable("sid") int sid,@PathVariable("type") String type )
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        List<Order> response=new ArrayList<Order>();
        if(existingManager==null)
        {
        	logger.warn("Store Manager Not Found Mgr_id="+mgr_id);
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
        	logger.warn("Store Not Found Sid="+sid);
            throw new NotFoundException("Store not found");
        }
        System.out.println(temp.getStoreList().size());
        List<Store> stores=temp.getStoreList().parallelStream()
                .filter(x->{System.out.println(x.getSid());
                        return x.getSid()==sid;}).collect(Collectors.toList());
        if(stores.size()==0)
        {
        	logger.warn("Store Not Found");
            throw new NotFoundException("Store not found");
        }
        Store s=storeService.fetchOrderList(stores.get(0).getSid());
        if(s==null)
        {
            return response;
        }

        List<Order>orders =s.getOrderList().parallelStream().filter(x->x.getOrder_type().equals(type)).collect(Collectors.toList());

        response.addAll(orders);
        logger.info("Order by Type Fetched Type="+type);
        return response;

    }

}
