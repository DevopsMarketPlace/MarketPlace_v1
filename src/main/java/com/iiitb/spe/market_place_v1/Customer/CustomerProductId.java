package com.iiitb.spe.market_place_v1.Customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class CustomerProductId implements Serializable {

    @Column(name="customer_id")
    private int customer_id;

    @Column(name="product_id")
    private int product_id;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerProductId that = (CustomerProductId) o;
        return getCustomer_id() == that.getCustomer_id() &&
                getProduct_id() == that.getProduct_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer_id(), getProduct_id());
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
