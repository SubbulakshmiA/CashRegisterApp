package com.example.cashregisterapp;

public class PurchasedItems extends Items{
    double total;
    PurchasedItems(String proType, int qnty, Double price,Double total) {
        super(proType, qnty, price);
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
