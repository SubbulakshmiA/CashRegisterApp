package com.example.cashregisterapp;

public class Items {
    String proType;
    int qnty;
    double price;
    Items(String proType, int qnty, Double price){
        this.proType = proType;
        this.qnty = qnty;
        this.price = price;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
