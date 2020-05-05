package com.iiitb.spe.market_place_v1.Product;

import com.iiitb.spe.market_place_v1.Customer.CustomerProduct;
import com.iiitb.spe.market_place_v1.Store.Store;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;
    @Column(length = 100,nullable = false)
    private String productname;
    @Column(nullable = false)
    private double pprice;
    @Column(length = 150)
    private String description;
    @Column(nullable = false)
    private double disprice;

    @OneToMany(mappedBy = "product")
    private List<ProductStore> productStoreList;

    @OneToMany(mappedBy = "product")
    private List<CustomerProduct> customerProductList;

    public Product(){

    }

    public List<ProductStore> getProductStoreList() {
        return productStoreList;
    }

    public void setProductStoreList(List<ProductStore> productStoreList) {
        this.productStoreList = productStoreList;
    }

    public List<CustomerProduct> getCustomerProductList() {
        return customerProductList;
    }

    public void setCustomerProductList(List<CustomerProduct> customerProductList) {
        this.customerProductList = customerProductList;
    }



    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDisprice() {
        return disprice;
    }

    public void setDisprice(double disprice) {
        this.disprice = disprice;
    }
}
