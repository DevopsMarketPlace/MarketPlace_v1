package com.iiitb.spe.market_place_v1.Address;

import com.iiitb.spe.market_place_v1.Customer.Customer;
import com.iiitb.spe.market_place_v1.Store.Store;

import javax.persistence.*;

@Entity

public class Address extends AddressSuperClass{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aid;
    @Column(length = 100, nullable = false)
    private String houseno;

    //primary




//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private Customer customer;



    public Address(){

    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }


}
