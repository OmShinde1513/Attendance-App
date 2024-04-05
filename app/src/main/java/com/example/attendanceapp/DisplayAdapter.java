package com.example.attendanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.viewHolder>{
    Context context;
    ArrayList<Model> displayList=new ArrayList<>();
    public DisplayAdapter(Context context)
    {
        this.context=context;
        this.displayList=ShowActivity.modelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.display_adapter,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.name.setText(displayList.get(position).getName());
    holder.prnNo.setText(displayList.get(position).getPRNno());
    holder.srNo.setText(ShowActivity.absentList.get(position));
    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView srNo,prnNo,name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            srNo=itemView.findViewById(R.id.srNo);
            prnNo=itemView.findViewById(R.id.prnNo);
            name=itemView.findViewById(R.id.name);
        }
    }
}
