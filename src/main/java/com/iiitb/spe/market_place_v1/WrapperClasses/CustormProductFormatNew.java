package com.iiitb.spe.market_place_v1.WrapperClasses;

public class CustormProductFormatNew {

    private String pname;
    private int quantity;

    public CustormProductFormatNew(String pname, int quantity) {
        this.pname = pname;
        this.quantity = quantity;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
