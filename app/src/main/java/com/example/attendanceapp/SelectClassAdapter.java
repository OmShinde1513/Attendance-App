package com.example.attendanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectClassAdapter extends RecyclerView.Adapter<SelectClassAdapter.viewHolder> {
    Context context;
    ArrayList<String>arrayList;
    public SelectClassAdapter(Context context,ArrayList<String>arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }
   public static ArrayList<String>ClassList=new ArrayList<>();
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_list,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.checkBox.setText(arrayList.get(position));
    holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b)
            {
                ClassList.add(compoundButton.getText().toString());
            }
            else{
                ClassList.remove(compoundButton.getText().toString());
            }
        }
    });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
        }
    }

}
