package com.iiitb.spe.market_place_v1.WrapperClasses;

public class CustomStoreFormat {

    private int sid;
    private String sname;
    private String pname;
    private int Quantity;

    public CustomStoreFormat(int sid, String sname, String pname, int quantity) {
        this.sid = sid;
        this.sname = sname;
        this.pname = pname;
        Quantity = quantity;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
