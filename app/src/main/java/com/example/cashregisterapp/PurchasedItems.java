package com.example.cashregisterapp;

public class PurchasedItems extends Items{
    double total;
    String purchaseDate;
    PurchasedItems(String proType, int qnty, Double price,Double total,String purchaseDate) {
        super(proType, qnty, price);
        this.total = total;
        this.purchaseDate = purchaseDate;
    }

}
