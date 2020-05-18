package com.iiitb.spe.market_place_v1.Store;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iiitb.spe.market_place_v1.Address.Address;
import com.iiitb.spe.market_place_v1.Address.AddressSuperClass;
import com.iiitb.spe.market_place_v1.CustomerStoreSlots.Slots;
import com.iiitb.spe.market_place_v1.Order.Order;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.Product.ProductStore;
import com.iiitb.spe.market_place_v1.StoreManager.StoreManager;

import javax.persistence.*;
import java.util.Date;
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

    @Temporal(value = TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Kolkata")
    @Column(name="start_time")
    private Date startTime;

    @Temporal(value = TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Kolkata")
    @Column(name="end_time")
    private Date endTime;

    @Column(name="no_of_person")
    private int noOfPerson;

    @Column(name="duration")
    private int duration;

    //in minutes

    @OneToMany(mappedBy = "store")
    private List<Slots> slotsList;

    @JsonIgnore //for fetching stores due to lazy fetch--aayush
    @OneToMany(mappedBy = "store")
    private List<ProductStore> productStoreList;


    @JsonIgnore //for fetching stores due to lazy fetch--aayush
    @OneToMany(mappedBy = "store")
    private List<Order> orderList;


    @ManyToOne
    @JoinColumn(name = "manager_id")
    private StoreManager storeManager;

    public List<Slots> getSlotsList() {
        return slotsList;
    }

    public void setSlotsList(List<Slots> slotsList) {
        this.slotsList = slotsList;
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getNoOfPerson() {
        return noOfPerson;
    }

    public void setNoOfPerson(int noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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
