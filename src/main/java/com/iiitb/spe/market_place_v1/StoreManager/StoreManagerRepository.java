package com.iiitb.spe.market_place_v1.StoreManager;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreManagerRepository extends CrudRepository<StoreManager,Integer> {

    Optional<StoreManager> findByUsername(String username);

    @Query("select s from StoreManager s join fetch s.storeList where s.uid=:uid")
    public Optional<StoreManager> findStoreList(@Param("uid") int uid);
}
