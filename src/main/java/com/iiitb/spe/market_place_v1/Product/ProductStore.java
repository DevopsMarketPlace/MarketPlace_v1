package com.iiitb.spe.market_place_v1.Product;

import com.iiitb.spe.market_place_v1.Store.Store;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductStore implements Serializable {

//    @EmbeddedId
//    private ProductStoreId productStoreId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="store_id")

    private Store store;

    @Column(name="quantity",nullable = false)
    private int quantity;

    @Column(name="disprice")
    private double dispirce;

    public ProductStore() {

    }

    public ProductStore(Product product, Store store, int quantity, double dispirce) {
        this.product = product;
        this.store = store;
        this.quantity = quantity;
        this.dispirce = dispirce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public double getDispirce() {
        return dispirce;
    }

    public void setDispirce(double dispirce) {
        this.dispirce = dispirce;
    }
}




