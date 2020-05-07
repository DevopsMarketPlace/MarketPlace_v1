package com.iiitb.spe.market_place_v1.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product,Integer> {
	
	@Query("select s from Product s join fetch s.productStoreList where s.pid=:id")
	Product getStoreList(@Param("id") int id);
}