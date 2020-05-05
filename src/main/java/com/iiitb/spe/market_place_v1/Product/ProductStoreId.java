package com.iiitb.spe.market_place_v1.Product;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductStoreId implements Serializable {

    @Column(name="product_id")
    private int productId;
    @Column(name="store_id")
    private int storeId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStoreId that = (ProductStoreId) o;
        return productId == that.productId &&
                storeId == that.storeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, storeId);
    }
}
