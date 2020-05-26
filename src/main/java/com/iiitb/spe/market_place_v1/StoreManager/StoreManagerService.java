package com.iiitb.spe.market_place_v1.StoreManager;

import com.iiitb.spe.market_place_v1.Store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreManagerService {


    @Autowired
    private StoreManagerRepository storeManagerRepository;


    public StoreManager fetchByUsername(String username)
    {
        return storeManagerRepository.findByUsername(username).orElse(null);
    }

    public StoreManager createStoreManager(StoreManager storeManager)
    {
        return storeManagerRepository.save(storeManager);
    }
    public StoreManager fetchById(int id)
    {
        return storeManagerRepository.findById(id).orElse(null);
    }
    public StoreManager getStores(int id)
    {
        return storeManagerRepository.findStoreList(id).orElse(null);
    }

}
