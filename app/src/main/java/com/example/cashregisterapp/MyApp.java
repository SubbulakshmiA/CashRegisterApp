package com.example.cashregisterapp;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

public class MyApp extends Application {
    ArrayList<Items> list;
    ArrayList<PurchasedItems> purchaseList = new ArrayList<PurchasedItems>();

    public ArrayList<Items> getList() {
        Log.d("myapp","in myapp getList"+list);
        if(list == null){
            list = new ArrayList<>();
            Items it1 = new Items("Pants",30,20.0);
            Items it2 = new Items("Shoes",30,20.0);
            Items it3 = new Items("Hats",30,20.0);
            list.add(it1);
            list.add(it2);
            list.add(it3);
            for(Items i :list){
                Log.d("myapp","in myapp "+list);
            }
        }
        return list;
    }

    public ArrayList<PurchasedItems> getPurchaseList() {
        return purchaseList;
    }


}
