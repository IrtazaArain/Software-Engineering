package com.example.taxgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class History_Adapter extends RecyclerView.Adapter<History_Adapter.MyViewHolder>{

    Context context;
    ArrayList<History_DataModal> history_dataModals;

    public History_Adapter(Context context, ArrayList<History_DataModal> history_dataModals){

        this.context = context;
        this.history_dataModals = history_dataModals;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_cardview_design, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.date.setText(history_dataModals.get(position).getDate());
        holder.time.setText(history_dataModals.get(position).getTime());
        holder.method.setText(history_dataModals.get(position).getMethod());
        holder.amount.setText(history_dataModals.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return history_dataModals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, time, method, amount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            date = itemView.findViewById(R.id.textViewDate);
            time = itemView.findViewById(R.id.textViewTime);
            method = itemView.findViewById(R.id.textViewMethod);
            amount = itemView.findViewById(R.id.textViewAmount);

        }
    }
}
