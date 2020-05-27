package com.iiitb.spe.market_place_v1.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iiitb.spe.market_place_v1.Address.AddressSuperClass;
import com.iiitb.spe.market_place_v1.CustomerStoreSlots.Slots;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.User.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends User {

    @Column(length = 100, nullable = false)
    private String houseno;

    @Embedded
    private AddressSuperClass address;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order>orderList;



    public Customer() {
        super();
    }


    public Customer(int uid, String firstname, String lastname, String contactno, String username, String password) {
        super(uid, firstname, lastname, contactno, username, password);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public AddressSuperClass getAddress() {
        return address;
    }

    public void setAddress(AddressSuperClass address) {
        this.address = address;
    }
}
