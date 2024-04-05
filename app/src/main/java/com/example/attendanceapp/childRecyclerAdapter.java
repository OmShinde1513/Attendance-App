package com.example.attendanceapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.SocketKeepalive;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import kotlin.reflect.KCallable;

public class childRecyclerAdapter extends RecyclerView.Adapter<childRecyclerAdapter.viewHolder> {
    Context context;
    ArrayList<String> childList;
    MakeCall call;

    childRecyclerAdapter(Context context, ArrayList<String>childList){
        this.context=context;
        this.childList=childList;
    }
    public void setCallBack(MakeCall call)
    {
        this.call=call;
    }


    @NonNull
    @Override
    public childRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chilld_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull childRecyclerAdapter.viewHolder holder, int position) {
           holder.textView.setText(childList.get(position));
          holder.textView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  boolean flag;
                  if(holder.textView.getCurrentTextColor()==Color.WHITE)
                  {
                      holder.textView.setBackgroundResource(R.drawable.bg);
                      holder.textView.setTextColor(Color.GRAY);
                      flag=true;
                  }
                   else{
                      holder.textView.setBackgroundResource(R.drawable.background);
                      holder.textView.setTextColor(Color.WHITE);
                      flag=false;
                  }
                  String s=holder.textView.getText().toString();
                  call.callBack(s,flag);

              }
          });
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }




    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview);

        }

    }


}
