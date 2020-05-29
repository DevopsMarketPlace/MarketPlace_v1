package com.iiitb.spe.market_place_v1.Store;

import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.StoreManager.StoreManager;

import com.iiitb.spe.market_place_v1.WrapperClasses.CustomProductFormat;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustomStoreFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class StoreController {
	
	Logger logger = LogManager.getLogger(StoreController.class);
    @Autowired
    private StoreService storeService;


    @PostMapping("/store/{mgr_id}")// ok tested
    public int createStore(@RequestBody Store store,@PathVariable("mgr_id") int mid)
    {

        StoreManager existingManager=storeService.fetchManagerById(mid);
        if(existingManager==null)
        {
        	logger.warn("Store Manager not found Mid="+mid);
            throw new NotFoundException("Store Manager not found");
        }
        Store newStore=storeService.createNewStore(store,existingManager);
        logger.info("Store Created "+newStore.getSid());
        return newStore.getSid();
    }

    @PutMapping("/store/{mid}")//ok tested
    public Store updateStore(@RequestBody Store store,@PathVariable("mid") int mid)
    {
        Store getStore=storeService.fetchStoreById(store.getSid());
        StoreManager existingManager=storeService.fetchManagerById(mid);
        if(getStore==null )
        {
        	logger.warn("Requested Store Not Found Sid="+store.getSid());
            throw new NotFoundException("Requested Store not found");
        }
        else if(existingManager==null)
        {
        	logger.warn("Store Manager Not Found");
            throw new NotFoundException("Store manager not found");
        }
        store.setStoreManager(existingManager);
        logger.info("Store Updated Sid="+store.getSid());
     return  storeService.updateStore(store);

    }
    @DeleteMapping("/store/{sid}") //ok tested
    public String deleteStore(@PathVariable("sid") int sid)
    {
        Store getStore=storeService.fetchStoreById(sid);
        if(getStore==null)
        {
        	logger.warn("Provided Store Not Found");
            throw new NotFoundException("Provided store not found");
        }
        storeService.removeStore(getStore);
        logger.info("Store Deleted Sid="+sid);
        return "Successfully deleted";
    }

    @GetMapping("/store/order")//ok tested
    public List<Order> getOrders(@RequestParam("sid") int sid)
    {
        Store getStore=storeService.fetchStoreById(sid);
        if(getStore==null)
        {
        	logger.warn("Provided Store Not Found Sid="+sid);
            throw new NotFoundException("Provided store not found");
        }
        Store response=storeService.fetchOrderList(sid);
        if(response==null)
        {
        	logger.warn("No orders found for Store" + getStore.getName());
            throw new NotFoundException("No orders found for Store" + getStore.getName());
        }
        logger.info("List of Order Fetched for Store sid="+sid);
        return response.getOrderList();
    }

    @GetMapping("/stores")//ok tested
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Store> getAllStores()
    {
        logger.info("All Stores Fetched");
        return storeService.fetchAllStores();

    }

    @GetMapping("/store/{sid}") //ok tested
    public Store getSpecificStore(@PathVariable("sid") int sid)
    {

        Store response = storeService.fetchStoreById(sid);
        if (response == null) {
        	logger.warn("Provided Store Not Found Sid="+sid);
            throw new NotFoundException("Provided Store not found");
        }
        logger.info("Store Fetched Sid"+sid);
        return response;


    }

    @GetMapping("/store/bypincode/{pincode}")//ok tested
    public List<Store> getStoreListbyPincode(@PathVariable("pincode") String pincode)
    {
        List<Store> getStores=storeService.fetchStorebyPincode(pincode);
        if(getStores.size()==0)
        {
        		logger.warn("No stores found for pincode" +pincode);
                throw new NotFoundException("No stores found for pincode " +pincode );
        }
        logger.info("Fetched The Products Based on Pincode="+pincode);
        return getStores;
    }



    @GetMapping("/store/products")//ok tested
    public List<Product> getProducts(@RequestParam("sid") int sid)
    {
        Store response = storeService.fetchStoreById(sid);
        if (response == null) {
        	logger.warn("Provided store not found Sid="+sid);
            throw new NotFoundException("Provided store not found");
        }

        Store result=storeService.fetchProductList(sid);
        if(result==null)
        {
        	logger.warn("No products found for Store=" + response.getName());
            throw new NotFoundException("No products found for Store" + response.getName());
        }
        logger.info("Products of Store Fetched sid="+sid);
        return result.getProductStoreList().parallelStream().map(x->x.getProduct()).collect(Collectors.toList());

    }
    @PostMapping("/test/{mid}")// ok tested
    public void test(@RequestBody Store store,@PathVariable("mid") int mid)
    {
        logger.info("Testing");
        System.out.println(mid);
        //return storeService.fetchAllStores();
    }

    @GetMapping("/store/product/{sid}")//ok tested
    public List<CustomProductFormat> getProduct(@PathVariable("sid") int sid)
    {
        Store response = storeService.fetchStoreById(sid);
        if (response == null) {
            logger.warn("Provided store not found Sid="+sid);
            throw new NotFoundException("Provided store not found");
        }

        Store result=storeService.fetchProductList(sid);
        if(result==null)
        {
            logger.warn("No products found for Store=" + response.getName());
            throw new NotFoundException("No products found for Store" + response.getName());
        }
        logger.info("Products of Store Fetched sid="+sid);
        return result.getProductStoreList().parallelStream().map(x->{return new CustomProductFormat(x.getProduct().getPid(),x.getProduct().getProductname(),x.getProduct().getPprice(),x.getDispirce(),x.getQuantity());
        }).collect(Collectors.toList());

    }

    @GetMapping("/store/slots/{sid}")
    public List<Date> getSlots(@PathVariable("sid") int sid)
    {
        Store s=storeService.fetchSlots(sid);
        List<Date> response=new ArrayList<Date>();
        if(s==null)
        {
            return response;
        }

        s.getSlotsList().forEach(x->response.add(x.getStartTime()));
       return response;
    }



}
