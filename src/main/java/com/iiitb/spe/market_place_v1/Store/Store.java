package com.iiitb.spe.market_place_v1.Store;

import com.iiitb.spe.market_place_v1.Address.Address;

import javax.persistence.*;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sid;

    @Embedded
    private Address Address;

    @Column(length = 100, nullable = false)
    private String name;

    public Store(){

    }

    public Store(com.iiitb.spe.market_place_v1.Address.Address address, String name) {
        Address = address;
        this.name = name;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public com.iiitb.spe.market_place_v1.Address.Address getAddress() {
        return Address;
    }

    public void setAddress(com.iiitb.spe.market_place_v1.Address.Address address) {
        Address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
