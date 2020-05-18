package com.iiitb.spe.market_place_v1.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iiitb.spe.market_place_v1.Address.Address;
import com.iiitb.spe.market_place_v1.CustomerStoreSlots.Slots;
import com.iiitb.spe.market_place_v1.Order.OrderProduct;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.User.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends User {
    @JsonIgnore
    @OneToMany
    @JoinTable(name="customer_address")
    private List<Address> addressList;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order>orderList;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Slots> slotsList;

    public Customer() {
        super();
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
