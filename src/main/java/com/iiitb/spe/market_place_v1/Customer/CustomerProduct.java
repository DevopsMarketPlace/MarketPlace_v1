package com.iiitb.spe.market_place_v1.Customer;

import com.iiitb.spe.market_place_v1.Product.Product;

import javax.persistence.*;

@Entity
@Table(name="Cutomer_Product")
public class CustomerProduct {

    @EmbeddedId
    private CustomerProductId customerProductId;

    @ManyToOne
    @MapsId("customer_id")
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="quantity",nullable = false)
    private int quantity;

    public CustomerProduct() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CustomerProductId getCustomerProductId() {
        return customerProductId;
    }

    public void setCustomerProductId(CustomerProductId customerProductId) {
        this.customerProductId = customerProductId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
