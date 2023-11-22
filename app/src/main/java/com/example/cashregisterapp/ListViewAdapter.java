package com.example.cashregisterapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    ArrayList<Items> list;
    Context activityContext;

//    DonationsBaseAdapter(ArrayList<Donation> list, Context context){
//        this.donationArrayList = list;
//        activityContext = context;
//    }
//
  ListViewAdapter(  ArrayList<Items> list, Context context){
    this.list = list;
    activityContext = context;
  }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {

      return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(activityContext).inflate(R.layout.list_view_items,viewGroup,false);
        TextView productTypeTv = view.findViewById(R.id.lv_product_type);
        TextView qntyTv = view.findViewById(R.id.lv_qnty_tv);
        TextView priceTv = view.findViewById(R.id.lv_price_tv);

        productTypeTv.setText(list.get(i).proType);
        qntyTv.setText(String.valueOf(list.get(i).qnty));
        priceTv.setText(String.valueOf(list.get(i).price));
        return view;
    }


}
