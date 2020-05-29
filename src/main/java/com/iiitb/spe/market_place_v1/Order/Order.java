package com.iiitb.spe.market_place_v1.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iiitb.spe.market_place_v1.Customer.Customer;
import com.iiitb.spe.market_place_v1.CustomerStoreSlots.Slots;
import com.iiitb.spe.market_place_v1.Store.Store;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="OrdersMade")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int oid;

    @Temporal(value = TemporalType.DATE)
    @Column(name="dateoforder",nullable = false)
    private Date dateOfOrder;

    @Column(name="status",nullable = false)
    private byte status; //1-placed ,2:- in processing, 3:-in delivered 4 :add to cart

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProductList;


    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Slots slots;


    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @Column(name="order_type")
    private String order_type;  //pickup delivery

    public Order() {
    }

    public Slots getSlots() {
        return slots;
    }

    public void setSlots(Slots slots) {
        this.slots = slots;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }
}
