package com.example.cashregisterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    ArrayList<Items> list;
    ArrayList<PurchasedItems> purchaseList;
    ListView listView;
    ListViewAdapter lvAdapter;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnC,btnBuy,managerBtn;
    TextView prodType,total,qnty;
    Items it ;
    String currentText;
    int rowSelected =-1 ;
    String selectedProdType;
    int qntyOrdered;
    double totalAmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            rowSelected = savedInstanceState.getInt("rowSelected");
            qntyOrdered = savedInstanceState.getInt("qntyOrdered");
            totalAmt = savedInstanceState.getDouble("totalAmt");

        }
        btn0 = findViewById(R.id.btn_0);
        btn0.setOnClickListener(this);
        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);
        btn8 = findViewById(R.id.btn_8);
        btn8.setOnClickListener(this);
        btn9 = findViewById(R.id.btn_9);
        btn9.setOnClickListener(this);
        btnC = findViewById(R.id.btn_c);
        btnC.setOnClickListener(this);
        btnBuy = findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(this);
        managerBtn = findViewById(R.id.manager_btn);
        managerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managerIntent = new Intent(MainActivity.this, ManagerPanelActivity.class);
                startActivity(managerIntent);
            }
        });

        prodType = findViewById(R.id.product_type);
        qnty = findViewById(R.id.qnty_tv);
        total = findViewById(R.id.total);

        // getting from MyApp
        list = ((MyApp)getApplication()).getList();
        purchaseList = ((MyApp)getApplication()).purchaseList;

        // listview
        listView = findViewById(R.id.list_view);
        lvAdapter = new ListViewAdapter( list,this);

        listView.setAdapter(lvAdapter);
        listView.setOnItemClickListener(this);
        if(rowSelected >=0){
            selectedProdType = ((MyApp)getApplication()).getList().get(rowSelected).proType;
            prodType.setText(selectedProdType);
            qnty.setText(String.valueOf(qntyOrdered));
            total.setText(String.valueOf(totalAmt));
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        selectedProdType = ((MyApp)getApplication()).getList().get(i).proType;
        prodType.setText(selectedProdType);
        rowSelected = i;
        if( !qnty.getText().toString().isEmpty()){
            totalAmt = qntyOrdered * list.get(rowSelected).price;
            total.setText((String.valueOf(totalAmt)));
        }
        lvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String btnString = btn.getText().toString();
        if(btn != btnC && btn != btnBuy){
            currentText = qnty.getText().toString();
            String updatedText = currentText +btnString;
            qntyOrdered = Integer.parseInt(updatedText);
            qnty.setText(String.valueOf(qntyOrdered));
            if( rowSelected>=0){
                totalAmt = qntyOrdered * list.get(rowSelected).price;
                total.setText((String.valueOf(totalAmt)));
            }
        }else if (btn == btnC){
            currentText = "";
            qnty.setText(currentText);
            total.setText(currentText);
            rowSelected = -1;
            qntyOrdered = 0;
            totalAmt = 0.0;
        } else {
            if( validateQnty() && !prodType.getText().toString().isEmpty() && !qnty.getText().toString().isEmpty() &&
                    !qnty.getText().toString().equals("0")){
                list.get(rowSelected).qnty =list.get(rowSelected).qnty - qntyOrdered;
                lvAdapter.notifyDataSetChanged();
                LocalDateTime timeNow = null;
                String purchaseDate ="";
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                   timeNow = LocalDateTime.now();
                    purchaseDate = dtf.format(timeNow);
                    PurchasedItems purchasedItemList =  new PurchasedItems(list.get(rowSelected).proType,qntyOrdered,
                            list.get(rowSelected).price,totalAmt,purchaseDate);
                    purchaseList.add(purchasedItemList);
                }
                System.out.println("time of purchase "+purchaseDate);
        // AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Thank you for your purchase");
                builder.setMessage("Your purchase is "+qntyOrdered+" " +list.get(rowSelected).proType+" for "+totalAmt);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                currentText = "";
                qnty.setText(currentText);
                prodType.setText(currentText);
                total.setText(currentText);
                rowSelected = -1;
                qntyOrdered = 0;
                totalAmt = 0.0;
            }else {
                Toast.makeText(this,"Please select an item and quantity ",Toast.LENGTH_LONG).show();
            }
            for(PurchasedItems i:purchaseList){
                System.out.println("i in purchaseList "+i.total);
            }
        }
    }
    boolean validateQnty(){
            if (rowSelected >= 0 && qntyOrdered <= list.get(rowSelected).qnty && !prodType.getText().toString().isEmpty()) {
                return  true;
        }
         else {
            currentText = "";
            qnty.setText(currentText);
            total.setText(currentText);
            Toast.makeText(this, "Please select an item and available quantity ", Toast.LENGTH_LONG).show();
            return  false;
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
//        int currentQnty = Integer.parseInt(currentText);
        outState.putInt("rowSelected",rowSelected);
        outState.putInt("qntyOrdered",qntyOrdered);
        outState.putDouble("totalAmt",totalAmt);
        super.onSaveInstanceState(outState);

    }

}