package com.iiitb.spe.market_place_v1.Customer;

import com.iiitb.spe.market_place_v1.Address.Address;
import com.iiitb.spe.market_place_v1.Product.Product;
import com.iiitb.spe.market_place_v1.Product.ProductStore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;
    @Column(length = 100,nullable = false)
    private String firstname;
    @Column(length = 100,nullable = false)
    private String lastname;
    @Column(length = 10,nullable = false, unique = true)
    private String contactno;

    @Column(length = 15,unique = true, nullable = false)
    private String username;

    @Column(length = 15,nullable = false)
    private String password;

    @OneToMany //extra relation
    @JoinTable(name="address_customer")
    private List<Address> addressList;

    @OneToMany(mappedBy="customer")
    private List<CustomerProduct> customerProductList;

    public List<CustomerProduct> getCustomerProductList() {
        return customerProductList;
    }

    public void setCustomerProductList(List<CustomerProduct> customerProductList) {
        this.customerProductList = customerProductList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public  Customer(){

    }
    public Customer(String firstname, String lastname, String contactno, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactno = contactno;
        this.username = username;
        this.password = password;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
