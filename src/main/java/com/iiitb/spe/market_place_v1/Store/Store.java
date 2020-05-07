package com.iiitb.spe.market_place_v1.Store;

import com.iiitb.spe.market_place_v1.Address.Address;
import com.iiitb.spe.market_place_v1.Address.AddressSuperClass;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.Product.ProductStore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sid;

    @Column(length = 100, nullable = false)
    private String name;


    @Embedded
    private AddressSuperClass address;

    @OneToMany(mappedBy = "store")
    private List<ProductStore> productStoreList;


    @OneToMany(mappedBy = "store")
    private List<Order> orderList;


    public Store(){

    }

    public List<ProductStore> getProductStoreList() {
        return productStoreList;
    }

    public void setProductStoreList(List<ProductStore> productStoreList) {
        this.productStoreList = productStoreList;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public AddressSuperClass getAddress() {
        return address;
    }

    public void setAddress(AddressSuperClass address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
