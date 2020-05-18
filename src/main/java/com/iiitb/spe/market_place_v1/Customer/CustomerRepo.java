package com.iiitb.spe.market_place_v1.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

<<<<<<< HEAD
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    @Query("select c from Customer  c join fetch c.orderList where c.uid=:id")
    Optional<Customer> fetchOrders(@Param("id")int id);
    Optional<Customer> findByUsername(String username);
=======
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    @Query("select c from Customer  c join fetch c.orderList where c.uid=:id")
    Customer fetchOrders(@Param("id")int id);
>>>>>>> cc728399fe099b69fdcf1333f42860a538faea55
}
