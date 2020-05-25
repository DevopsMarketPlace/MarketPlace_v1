package com.iiitb.spe.market_place_v1.Store;

import com.iiitb.spe.market_place_v1.StoreManager.StoreManager;
import com.iiitb.spe.market_place_v1.StoreManager.StoreManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreManagerService storeManagerService;

    public Store createNewStore(Store s,StoreManager manager)
    {
        s.setStoreManager(manager);
        return storeRepository.save(s);
    }

    public Store updateStore(Store upStore)
    {

        return storeRepository.save(upStore);
    }

    public void removeStore(Store existingStore)
    {
        storeRepository.delete(existingStore);
    }
    public List<Store> fetchAllStores()
    {
        List<Store> response=new ArrayList<Store>();
        storeRepository.findAll().forEach(response::add);
        return response;
    }
    public Store fetchStoreById(int id)  {


        return storeRepository.findById(id).orElse(null);


    }
    public Store fetchProductList(int id)
    {
        return storeRepository.fetchProducts(id);
    }

    public Store fetchOrderList(int id){
        return storeRepository.fetchOrders(id);
    }

    public List<Store> fetchStorebyPincode(String pincode)
    {
        return storeRepository.findByAddress_Pincode(pincode);
    }

    public StoreManager fetchManagerById(int id)
    {
        return storeManagerService.fetchById(id);
    }
}
