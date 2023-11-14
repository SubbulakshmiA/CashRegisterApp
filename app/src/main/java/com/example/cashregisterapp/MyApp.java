package com.example.cashregisterapp;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
    ArrayList<Items> list = new ArrayList<Items>();
    ArrayList<PurchasedItems> purchaseList = new ArrayList<PurchasedItems>();
    Items it1 = new Items("Pants",30,20.0);
    Items it2 = new Items("Shoes",30,20.0);
    Items it3 = new Items("Hats",30,20.0);


}
