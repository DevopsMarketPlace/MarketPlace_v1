package com.iiitb.spe.market_place_v1.WrapperClasses;

public class CustomProductFormat {

    private int pid;
    private String pname;
    private double pprice;
    private double disprice;
    private int quantity;

    public CustomProductFormat(int pid, String pname, double pprice, double disprice, int quantity) {
        this.pid = pid;
        this.pname = pname;
        this.pprice = pprice;
        this.disprice = disprice;
        this.quantity = quantity;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPprice() {
        return pprice;
    }

    public void setPprice(double pprice) {
        this.pprice = pprice;
    }

    public double getDisprice() {
        return disprice;
    }

    public void setDisprice(double disprice) {
        this.disprice = disprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
