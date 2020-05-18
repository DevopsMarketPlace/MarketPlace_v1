package com.iiitb.spe.market_place_v1.Order;

import com.iiitb.spe.market_place_v1.Product.Product;

import java.util.List;

public class Demo {

    private int cid;

    private int sid;

    private List<Product> productList;

    private List<Double> quantityList;

    public List<Double> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(List<Double> quantityList) {
        this.quantityList = quantityList;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
