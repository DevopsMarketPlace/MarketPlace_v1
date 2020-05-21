package com.iiitb.spe.market_place_v1.StoreManager;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StoreManagerRepository extends CrudRepository<StoreManager,Integer> {

    Optional<StoreManager> findByUsername(String username);
}
