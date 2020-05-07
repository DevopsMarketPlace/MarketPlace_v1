package com.iiitb.spe.market_place_v1.Order;

import com.iiitb.spe.market_place_v1.Customer.Customer;
import com.iiitb.spe.market_place_v1.Order.OrderProductId;
import com.iiitb.spe.market_place_v1.Product.Product;

import javax.persistence.*;

@Entity
@Table(name="Order_Product")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId orderProductId;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @MapsId("product_id")
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

    public OrderProductId getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(OrderProductId orderProductId) {
        this.orderProductId = orderProductId;
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
