package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerPanelActivity extends AppCompatActivity {

    Button restockBtn,historybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);

        restockBtn = findViewById(R.id.restock_btn);
        restockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managerIntent = new Intent(ManagerPanelActivity.this, RestockActivity.class);
                startActivity(managerIntent);
            }
        });
        historybtn = findViewById(R.id.history_btn);
        historybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managerIntent = new Intent(ManagerPanelActivity.this, HistoryActivity.class);
                startActivity(managerIntent);
            }
        });
    }
}