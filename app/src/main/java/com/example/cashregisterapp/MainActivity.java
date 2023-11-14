package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    ArrayList<Items> list;
    ArrayList<PurchasedItems> purchaseList;
    ListView listView;
    ListViewAdapter lvAdapter;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnC,btnBuy;
    TextView prodType,total,qnty;
    Items it ;
    String currentText;
    int rowSelected = 0;
    int qntyOrdered = 0;
    double totalAmt = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        prodType = findViewById(R.id.productType);
        qnty = findViewById(R.id.qnty_tv);
        total = findViewById(R.id.total);

        list = ((MyApp)getApplication()).list;
        list.add(((MyApp)getApplication()).it1);
        list.add(((MyApp)getApplication()).it2);
        list.add(((MyApp)getApplication()).it3);

        purchaseList = ((MyApp)getApplication()).purchaseList;

        listView = findViewById(R.id.list_view);

//       Items it1 = new Items("Pants",30,20.0);
//       Items it2 = new Items("Shoes",30,20.0);
//       Items it3 = new Items("Hats",30,20.0);
//        list.add(it1);
//        list.add(it2);
//        list.add(it3);
        lvAdapter =
                new ListViewAdapter( list,this);

        listView.setAdapter(lvAdapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("adapter", "clicked ittem at "+i );
        lvAdapter.onItemClick(i,view);
        prodType.setText(list.get(i).proType);
        rowSelected = i;
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String btnString = btn.getText().toString();
        if(btn != btnC && btn != btnBuy){
            currentText = qnty.getText().toString();
            String updatedText = currentText +btnString;
            qntyOrdered = Integer.parseInt(updatedText);
            if(qntyOrdered <= list.get(rowSelected).qnty){
                qnty.setText(updatedText);
                totalAmt = qntyOrdered * list.get(rowSelected).price;
                total.setText((String.valueOf(totalAmt)));
            }else{
                currentText = "";
                qnty.setText(currentText);
                Toast.makeText(this,"Please select available quantity ",Toast.LENGTH_LONG).show();
            }

        }else if (btn == btnC){
            currentText = "";
            qnty.setText(currentText);
        } else {
            PurchasedItems purchasedItemList = new PurchasedItems(list.get(rowSelected).proType,list.get(rowSelected).qnty,
                    list.get(rowSelected).price,totalAmt);
            purchaseList.add(purchasedItemList);
            for(PurchasedItems i:purchaseList){
                System.out.println("i in purchaseList "+i.total);
            }
        }
    }
}