package com.iiitb.spe.market_place_v1.StoreManager;

import com.iiitb.spe.market_place_v1.Exceptions.FoundException;
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class StoreManagerController {

    @Autowired
    private StoreManagerService storeManagerService;

    @GetMapping("/storemanager/login")
    public int authenticate(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        StoreManager existingManager=storeManagerService.fetchByUsername(username);
        if(existingManager==null)
        {
            throw new NotFoundException("username or password incorrec");
        }

        return existingManager.getUid();
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
}
