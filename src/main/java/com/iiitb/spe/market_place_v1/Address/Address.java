package com.iiitb.spe.market_place_v1.Address;

import com.iiitb.spe.market_place_v1.Customer.Customer;
import com.iiitb.spe.market_place_v1.Store.Store;

import javax.persistence.*;

@Entity

public class Address extends AddressSuperClass{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aid;


//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private Customer customer;



    public Address(){

    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }


}
