package com.iiitb.spe.market_place_v1.Store;

import com.iiitb.spe.market_place_v1.Address.Address;
import com.iiitb.spe.market_place_v1.Address.AddressSuperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;


    public Store createNewStore(Store s)
    {
        return storeRepository.save(s);
    }

    public Store updateStore(Store upStore, Store existingStore)
    {
        existingStore.setName(upStore.getName());
        existingStore.setAddress(upStore.getAddress());
        return storeRepository.save(existingStore);
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
    public Store fetchSpecificStore(int id)  {


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


}
