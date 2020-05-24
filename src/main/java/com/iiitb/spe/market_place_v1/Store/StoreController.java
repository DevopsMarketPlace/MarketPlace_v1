package com.iiitb.spe.market_place_v1.Store;

import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.StoreManager.StoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class StoreController {
    @Autowired
    private StoreService storeService;


    @PostMapping("/store/{mgr_id}")// ok tested
    public int createStore(@RequestBody Store store,@PathVariable("mgr_id") int mid)
    {

        StoreManager existingManager=storeService.fetchManagerById(mid);
        if(existingManager==null)
        {
            throw new NotFoundException("Store Manager not found");
        }
        Store newStore=storeService.createNewStore(store,existingManager);

        return newStore.getSid();
    }

    @PutMapping("/store/{mid}")//ok tested
    public Store updateStore(@RequestBody Store store,@PathVariable("mid") int mid)
    {
        Store getStore=storeService.fetchStoreById(store.getSid());
        StoreManager existingManager=storeService.fetchManagerById(mid);
        if(getStore==null )
        {
            throw new NotFoundException("Requested Store not found");
        }
        else if(existingManager==null)
        {
            throw new NotFoundException("Store manager not found");
        }
        store.setStoreManager(existingManager);
     return  storeService.updateStore(store);

    }
    @DeleteMapping("/store/{sid}") //ok tested
    public String deleteStore(@PathVariable("sid") int sid)
    {
        Store getStore=storeService.fetchStoreById(sid);
        if(getStore==null)
        {
            throw new NotFoundException("Provided store not found");
        }
        storeService.removeStore(getStore);
        return "Successfully deleted";
    }

    @GetMapping("/store/order")//ok tested
    public List<Order> getOrders(@RequestParam("sid") int sid)
    {
        Store getStore=storeService.fetchStoreById(sid);
        if(getStore==null)
        {
            throw new NotFoundException("Provided store not found");
        }
        Store response=storeService.fetchOrderList(sid);
        if(response==null)
        {
            throw new NotFoundException("No orders found for Store" + getStore.getName());
        }
        return response.getOrderList();
    }

    @GetMapping("/stores")//ok tested
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Store> getAllStores()
    {

        return storeService.fetchAllStores();

    }

    @GetMapping("/store/{sid}") //ok tested
    public Store getSpecificStore(@PathVariable("sid") int sid)
    {

        Store response = storeService.fetchStoreById(sid);
        if (response == null) {
            throw new NotFoundException("Provided Store not found");
        }
        return response;


    }

    @GetMapping("/store/bypincode")//ok tested
    public List<Store> getStoreListbyPincode(@RequestParam("pincode") String pincode)
    {
        List<Store> getStores=storeService.fetchStorebyPincode(pincode);
        if(getStores.size()==0)
        {
                throw new NotFoundException("No stores found for pincode " +pincode );
        }
        return getStores;
    }



    @GetMapping("/store/products")//ok tested
    public List<Product> getProducts(@RequestParam("sid") int sid)
    {
        Store response = storeService.fetchStoreById(sid);
        if (response == null) {
            throw new NotFoundException("Provided store not found");
        }

        Store result=storeService.fetchProductList(sid);
        if(result==null)
        {
            throw new NotFoundException("No products found for Store" + response.getName());
        }

        return result.getProductStoreList().parallelStream().map(x->x.getProduct()).collect(Collectors.toList());

    }
    @PostMapping("/test/{mid}")// ok tested
    public void test(@RequestBody Store store,@PathVariable("mid") int mid)
    {
        System.out.println(mid);
        //return storeService.fetchAllStores();
    }

}
