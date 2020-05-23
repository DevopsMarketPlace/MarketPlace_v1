package com.iiitb.spe.market_place_v1.StoreManager;

import com.iiitb.spe.market_place_v1.Exceptions.FoundException;
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StoreManagerController {

    @Autowired
    private StoreManagerService storeManagerService;

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

}
