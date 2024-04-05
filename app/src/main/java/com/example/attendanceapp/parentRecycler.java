package com.example.attendanceapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class parentRecycler extends RecyclerView.Adapter<parentRecycler.viewHolder> {
    Context context;
    HashMap<Integer,ArrayList<String>>hashMap;
    childRecyclerAdapter ChildAdapter;

    public parentRecycler(Context context, HashMap<Integer,ArrayList<String>>hashMap)
    {
        this.context=context;
        this.hashMap=hashMap;
    }
    @NonNull
    @Override
    public parentRecycler.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.parent_layout,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull parentRecycler.viewHolder holder, int position) {
    holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
     ChildAdapter=new childRecyclerAdapter(context,hashMap.get(position));

    holder.recyclerView.setAdapter(ChildAdapter);
    ChildAdapter.setCallBack((MakeCall) context);
//    adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return hashMap.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
     RecyclerView recyclerView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.recycle);
        }
    }
}
