package com.iiitb.spe.market_place_v1.Address;

import javax.persistence.*;

@MappedSuperclass
@Embeddable
public class AddressSuperClass {


    @Column(length = 6 , nullable = false)
    private String pincode;
    @Column(length = 100, nullable = false)
    private String city;
//    @Column(length = 100, nullable = false)
//    private String state;
    @Column(nullable = false)
    private  double lat;
    @Column(nullable = false)
    private  double lon;



    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
