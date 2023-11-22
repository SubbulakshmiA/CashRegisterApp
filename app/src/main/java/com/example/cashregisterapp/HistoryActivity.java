package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener  {

    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdapter;
    ArrayList<PurchasedItems> purchaseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Log.d("history","onCreate hist activity");
        purchaseList = ((MyApp)getApplication()).getPurchaseList();
        recyclerView = findViewById(R.id.recycler_view);
        rvAdapter = new RecyclerViewAdapter(purchaseList,this);
        rvAdapter.listener = this;
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public void onItemClicked(int position) {
        Intent in = new Intent(HistoryActivity.this, DetailHistoryView.class);
        in.putExtra("position",position);
        startActivity(in);
    }
}