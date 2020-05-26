package com.iiitb.spe.market_place_v1.StoreManager;

import com.iiitb.spe.market_place_v1.Exceptions.FoundException;
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.Product.ProductStore;
import com.iiitb.spe.market_place_v1.Store.Store;
import com.iiitb.spe.market_place_v1.Store.StoreService;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustomStoreFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class StoreManagerController {

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
            throw new NotFoundException("username or password incorrect");
        }
        if(!password.equals(existingManager.getPassword()))
        {
            throw new NotFoundException("password incorrect");
        }

        return existingManager;
    }
    @PostMapping("/storemanager")
    public void createStoreManager(@RequestBody StoreManager storeManager)
    {
        StoreManager existingManager=storeManagerService.fetchByUsername(storeManager.getUsername());
        if(existingManager!=null)
        {
            throw new FoundException("username already taken!! try different");
        }
        storeManagerService.createStoreManager(storeManager);

    }

    @GetMapping("/storemanager/stores/{mgr_id}")
    public List<Store> getAllStores(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        if(existingManager==null)
        {
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
            throw new FoundException("No Stores found");
        }
        return temp.getStoreList();

    }
    @GetMapping("/storemanager/stores/{mgr_id}/count")
    public int getStoreCount(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        if(existingManager==null)
        {
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
            return 0;
        }
        return temp.getStoreList().size();
    }

    @GetMapping("/storemanager/stores/{mgr_id}/totalordercount")
    public int getTotalOrderCount(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        if(existingManager==null)
        {
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
            return 0;
        }


       List<Integer> countList= temp.getStoreList().parallelStream().map(x->{

             Store s=storeService.fetchOrderList(x.getSid());
             if(s==null)
                 return 0;
             return s.getOrderList().size();

         }).collect(Collectors.toList());
       return  countList.parallelStream().mapToInt(Integer::valueOf).sum();
    }

    @GetMapping("/storemanager/stores/{mgr_id}/ordercount/type")
    public List<Integer> getTotalOrderCountByType(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        List<Integer> result=new ArrayList<Integer>();
        if(existingManager==null)
        {
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
            result.add(0);
            return result;
        }


//        List<Integer> countList= temp.getStoreList().parallelStream().map(x->{
//
//            Store s=storeService.fetchOrderList(x.getSid());
//            if(s==null)
//                return 0;
//            return s.getOrderList().parallelStream().filter(y->y.getOrder_type().equals("Delivery")).collect(Collectors.toList()).size();
//        }).collect(Collectors.toList());
//        int a = countList.parallelStream().mapToInt(Integer::valueOf).sum();
        Store temp1 =storeService.fetchOrderListByType("delivery");
        if(temp1==null)
        {
            result.add(0);
        }
        else{
            int a=temp1.getOrderList().size();
            result.add(a);
        }


        temp1=storeService.fetchOrderListByType("pickup");
        if(temp1==null)
        {
            result.add(0);
        }
        else {
            int b = temp1.getOrderList().size();
            result.add(b);
        }

        return result ;
    }
    @GetMapping("/storemanager/stores/inventory/{mgr_id}")
    public List<CustomStoreFormat> getInventoryAlerts(@PathVariable("mgr_id") int mgr_id)
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        if(existingManager==null)
        {
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


        return  response;
    }

    @GetMapping("/storemanager/{mgr_id}/store/{sid}/orders/{type}")
    public List<Order> getOrderByType(@PathVariable("mgr_id") int mgr_id,@PathVariable("sid") int sid,@PathVariable("type") String type )
    {
        StoreManager existingManager=storeManagerService.fetchById(mgr_id);
        List<Order> response=new ArrayList<Order>();
        if(existingManager==null)
        {
            throw new NotFoundException("Store Manager not found");
        }
        StoreManager temp=storeManagerService.getStores(existingManager.getUid());
        if(temp==null)
        {
            throw new NotFoundException("Store not found");
        }
        System.out.println(temp.getStoreList().size());
        List<Store> stores=temp.getStoreList().parallelStream()
                .filter(x->{System.out.println(x.getSid());
                        return x.getSid()==sid;}).collect(Collectors.toList());
        if(stores.size()==0)
        {
            throw new NotFoundException("Store not found");
        }
        Store s=storeService.fetchOrderList(stores.get(0).getSid());
        if(s==null)
        {
            return response;
        }

        List<Order>orders =s.getOrderList().parallelStream().filter(x->x.getOrder_type().equals(type)).collect(Collectors.toList());

        response.addAll(orders);
        return response;

    }

}
