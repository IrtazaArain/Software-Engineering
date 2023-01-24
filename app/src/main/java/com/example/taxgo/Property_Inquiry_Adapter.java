package com.example.taxgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Property_Inquiry_Adapter extends RecyclerView.Adapter<Property_Inquiry_Adapter.MyViewHolder>{

    Context context;
    ArrayList<Property_Inquiry_DataModal> property_inquiry_dataModals;

    public Property_Inquiry_Adapter(Context context, ArrayList<Property_Inquiry_DataModal> property_inquiry_dataModals){

        this.context = context;
        this.property_inquiry_dataModals = property_inquiry_dataModals;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.property_inq_cardview_design, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.property_type.setText(property_inquiry_dataModals.get(position).getProperty_Type());
        holder.city.setText(property_inquiry_dataModals.get(position).getCity());
        holder.address.setText(property_inquiry_dataModals.get(position).getAddress());
        holder.land_area.setText(property_inquiry_dataModals.get(position).getLand_Area());
        holder.covered_area.setText(property_inquiry_dataModals.get(position).getCovered_Area());
        holder.construction_type.setText(property_inquiry_dataModals.get(position).getConstruction_Type());


    }

    @Override
    public int getItemCount() {
        return property_inquiry_dataModals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView property_type, city, address, land_area, covered_area, construction_type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            property_type = itemView.findViewById(R.id.textViewPropertyType);
            city = itemView.findViewById(R.id.textViewCity);
            address = itemView.findViewById(R.id.textViewAddress);
            land_area = itemView.findViewById(R.id.textViewLandArea);
            covered_area = itemView.findViewById(R.id.textViewCoveredArea);
            construction_type = itemView.findViewById(R.id.textViewConstructionType);

        }
    }
}
