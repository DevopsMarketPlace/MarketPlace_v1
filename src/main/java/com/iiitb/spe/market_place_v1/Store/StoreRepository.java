package com.iiitb.spe.market_place_v1.Store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends CrudRepository<Store,Integer> {

    @Query("select s from Store s join fetch s.productStoreList where s.sid=:id")
     Store fetchProducts(@Param("id") int id);

    @Query("select s from Store s join fetch s.orderList where s.sid=:id")
    Store fetchOrders(@Param("id") int id);

    List<Store> findByAddress_Pincode(String pincode);
}
