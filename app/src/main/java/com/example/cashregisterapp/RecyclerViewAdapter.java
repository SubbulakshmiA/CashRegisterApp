package com.example.cashregisterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderItems> {

    interface ItemClickListener{
        void onItemClicked(int position);
    }
    ArrayList<PurchasedItems> list;
    Context activityContext;
    ItemClickListener listener;
    RecyclerViewAdapter( ArrayList<PurchasedItems> list,Context activityContext){
        this.activityContext = activityContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activityContext).inflate(R.layout.list_view_items,parent,false);
        return new ViewHolderItems(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItems holder, int position) {
       holder.priceTv.setText(String.valueOf(list.get(position).total));
       holder.prodTypeTv.setText(list.get(position).proType);
       holder.qntyTv.setText(String.valueOf(list.get(position).qnty));

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.onItemClicked(holder.getAdapterPosition());
           }
       });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderItems extends RecyclerView.ViewHolder{// implements ItemClickListener {
        public ViewHolderItems(@NonNull View itemView) {
            super(itemView);
        }
        TextView prodTypeTv = itemView.findViewById(R.id.lv_product_type);
        TextView qntyTv = itemView.findViewById(R.id.lv_qnty_tv);
        TextView priceTv = itemView.findViewById(R.id.lv_price_tv);


        }
}
