package com.iiitb.spe.market_place_v1.Product;

import com.iiitb.spe.market_place_v1.Store.Store;

import javax.persistence.*;

@Entity
public class ProductStore{

    @EmbeddedId
    private ProductStoreId productStoreId;

    @ManyToOne
    @JoinColumn(name="product_id")
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name="store_id")
    @MapsId("storeId")
    private Store store;

    @Column(name="quantity",nullable = false)
    private int quantity;

    public ProductStore() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductStoreId getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(ProductStoreId productStoreId) {
        this.productStoreId = productStoreId;
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
}




