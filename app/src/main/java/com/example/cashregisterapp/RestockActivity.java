package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity {

    Button okbtn,cancelBtn;
    EditText restockEidtText;
    ListView listView;
    ArrayList<Items> list;
    ListViewAdapter lvAdapter;
    int rowSelected = -1;
    int row = -1;
    int newQunty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        restockEidtText = findViewById(R.id.restock_etv);
        list = ((MyApp)getApplication()).getList();
        // listview
        listView = findViewById(R.id.list_view);
        lvAdapter = new ListViewAdapter( list,this);

        listView.setAdapter(lvAdapter);
        restockEidtText = findViewById(R.id.restock_etv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//              view.setBackgroundColor(getResources().getColor(R.color.Pink));
                rowSelected = position;
                if(row >= 0){
                    parent.getChildAt(row).setBackgroundColor(getColor(R.color.white));
                 }
                parent.getChildAt(position).setBackgroundColor(getColor(R.color.Pink));
                row = position;

            }
        });

        okbtn = findViewById(R.id.ok_btn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rowSelected >=0 && !restockEidtText.getText().toString().isEmpty()) {
                    newQunty = Integer.parseInt(restockEidtText.getText().toString());
                    list.get(rowSelected).qnty = newQunty;
                    lvAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(RestockActivity.this,"Please select an item to restock the quantity ",Toast.LENGTH_LONG).show();
                }
            }
        });
        cancelBtn = findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(RestockActivity.this, MainActivity.class);
                startActivity(in);
            }
        });


    }

}