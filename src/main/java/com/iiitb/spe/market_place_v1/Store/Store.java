package com.iiitb.spe.market_place_v1.Store;

import com.iiitb.spe.market_place_v1.Address.Address;
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

    @OneToMany(mappedBy = "store")
    private List<ProductStore> productStoreList;
    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;


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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
