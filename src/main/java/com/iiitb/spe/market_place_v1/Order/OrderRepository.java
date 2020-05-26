package com.iiitb.spe.market_place_v1.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order,Integer> {

    @Query("select o from Order o join fetch o.orderProductList where o.oid=:oid")
    Optional<Order> fetchProducts(@Param("oid") int oid);
}
