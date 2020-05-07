package com.iiitb.spe.market_place_v1.Order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class OrderProductId implements Serializable {

    @Column(name="order_id")
    private int order_id;

    @Column(name="product_id")
    private int product_id;


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductId that = (OrderProductId) o;
        return getOrder_id() == that.getOrder_id() &&
                getProduct_id() == that.getProduct_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder_id(), getProduct_id());
    }
}
