package com.example.taxgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Vehicle_Inquiry_Adapter extends RecyclerView.Adapter<Vehicle_Inquiry_Adapter.MyViewHolder>{

    Context context;
    ArrayList<Vehicle_Inquiry_DataModal> vehicle_inquiry_dataModals;

    public Vehicle_Inquiry_Adapter(Context context, ArrayList<Vehicle_Inquiry_DataModal> vehicle_inquiry_dataModals){

        this.context = context;
        this.vehicle_inquiry_dataModals = vehicle_inquiry_dataModals;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vehicle_inq_cardview_design, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.vehicle_class.setText(vehicle_inquiry_dataModals.get(position).getVehicle_Class());
        holder.purchase_type.setText(vehicle_inquiry_dataModals.get(position).getPurchase_Type());
        holder.ownership_status.setText(vehicle_inquiry_dataModals.get(position).getOwnership_status());
        holder.price.setText(vehicle_inquiry_dataModals.get(position).getPrice());
        holder.seating_capacity.setText(vehicle_inquiry_dataModals.get(position).getSeating_Capacity());
        holder.import_purchase_date.setText(vehicle_inquiry_dataModals.get(position).getImport_Purchase_Date());
        holder.engine_size.setText(vehicle_inquiry_dataModals.get(position).getEngine_Size());

    }

    @Override
    public int getItemCount() {
        return vehicle_inquiry_dataModals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vehicle_class, purchase_type, ownership_status, price, seating_capacity,
                import_purchase_date, engine_size;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            vehicle_class = itemView.findViewById(R.id.textViewVehicleClass);
            purchase_type = itemView.findViewById(R.id.textViewVehiclePurchase);
            ownership_status = itemView.findViewById(R.id.textViewOwnerStatus);
            price = itemView.findViewById(R.id.textViewVehiclePrice);
            seating_capacity = itemView.findViewById(R.id.textViewSeatingCapacity);
            import_purchase_date = itemView.findViewById(R.id.textViewImportPurchaseDate);
            engine_size = itemView.findViewById(R.id.textViewEngineSize);

        }
    }
}
