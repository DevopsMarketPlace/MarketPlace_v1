package com.iiitb.spe.market_place_v1.Address;

import javax.persistence.*;

@Entity
@Embeddable
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aid;
    @Column(length = 100, nullable = false)
    private String houseno;
    @Column(length = 6 , nullable = false)
    private String pincode;
    @Column(length = 100, nullable = false)
    private String city;
    @Column(length = 100, nullable = false)
    private String state;
    @Column(nullable = false)
    private  double lat;
    @Column(nullable = false)
    private  double lon;

    public Address(){

    }
    public Address(String houseno, String pincode, String city, String state, double lat, double lon) {
        this.houseno = houseno;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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
