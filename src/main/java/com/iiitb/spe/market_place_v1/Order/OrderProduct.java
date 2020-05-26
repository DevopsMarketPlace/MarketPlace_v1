package com.iiitb.spe.market_place_v1.Order;

import com.iiitb.spe.market_place_v1.Product.Product;

import javax.persistence.*;

@Entity
@Table(name="Order_Product")
public class OrderProduct {

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private int id;


    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="quantity",nullable = false)
    private int quantity;

    public OrderProduct() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
