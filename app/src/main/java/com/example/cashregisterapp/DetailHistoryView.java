package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailHistoryView extends AppCompatActivity {

    TextView detailProdTv,detailPriceTv,detailDateTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_view);

        ArrayList<PurchasedItems> purchaseList = ((MyApp)getApplication()).getPurchaseList();
        detailProdTv = findViewById(R.id.detail_prod_tv);
        detailPriceTv = findViewById(R.id.detail_price_tv);
        detailDateTv = findViewById(R.id.detail_date_tv);

        Intent in = getIntent();
        int position = in.getIntExtra("position",0);

        detailProdTv.setText(String.format("%s%s", detailProdTv.getText().toString(), purchaseList.get(position).proType));
        String s = detailPriceTv.getText().toString() + (purchaseList.get(position).total);
        detailPriceTv.setText(s);
        String s1 = detailDateTv.getText().toString() + (purchaseList.get(position).purchaseDate);
        detailDateTv.setText(s1);

    }
}