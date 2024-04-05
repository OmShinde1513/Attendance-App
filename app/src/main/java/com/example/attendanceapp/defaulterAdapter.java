package com.example.attendanceapp;


import android.graphics.Color;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class defaulterAdapter extends RecyclerView.Adapter<defaulterAdapter.viewHolder> {
    Context context;
    ArrayList<listModel> arrayList;
    public defaulterAdapter(Context context,ArrayList<listModel>arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.sample, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
         holder.rollNo.setText(arrayList.get(position).getRollNo());
         holder.name.setText(arrayList.get(position).getName());
         holder.ADBMS.setText(String.valueOf(arrayList.get(position).getADBMS()));
         holder.CNA.setText(String.valueOf(arrayList.get(position).getCNA()));
         holder.CT.setText(String.valueOf(arrayList.get(position).getCT()));
         holder.FLAT.setText(String.valueOf(arrayList.get(position).getFLAT()));
         holder.SEAD.setText(String.valueOf(arrayList.get(position).getSEAD()));
         float Total=(arrayList.get(position).getADBMS()+arrayList.get(position).getCNA()+arrayList.get(position).getCT()+arrayList.get(position).getFLAT()+arrayList.get(position).getSEAD())/DefaulterList.t;
        DecimalFormat format =new DecimalFormat("0.00");
         holder.TOTAL.setText(format.format(Total));
         float t=Float.parseFloat(format.format(Total));
         if(t<75.00){
             holder.rollNo.setTextColor(Color.RED);
             holder.name.setTextColor(Color.RED);
             holder.ADBMS.setTextColor(Color.RED);
             holder.CNA.setTextColor(Color.RED);
             holder.CT.setTextColor(Color.RED);
             holder.FLAT.setTextColor(Color.RED);
             holder.SEAD.setTextColor(Color.RED);
             holder.TOTAL.setTextColor(Color.RED);
         }
         else{
             holder.rollNo.setTextColor(Color.GREEN);
             holder.name.setTextColor(Color.GREEN);
             holder.ADBMS.setTextColor(Color.GREEN);
             holder.CNA.setTextColor(Color.GREEN);
             holder.CT.setTextColor(Color.GREEN);
             holder.FLAT.setTextColor(Color.GREEN);
             holder.SEAD.setTextColor(Color.GREEN);
             holder.TOTAL.setTextColor(Color.GREEN);
         }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView rollNo,name,ADBMS,CNA,CT,FLAT,SEAD,TOTAL;

       public viewHolder(@NonNull View itemView) {
           super(itemView);
           rollNo=itemView.findViewById(R.id.Rollno);
           name=itemView.findViewById(R.id.Name);
           ADBMS=itemView.findViewById(R.id.ADBMS);
           CNA=itemView.findViewById(R.id.CNA);
           CT=itemView.findViewById(R.id.CT);
           FLAT=itemView.findViewById(R.id.FLAT);
           SEAD=itemView.findViewById(R.id.SEAD);
           TOTAL=itemView.findViewById(R.id.TOTAL);
       }
   }

}
