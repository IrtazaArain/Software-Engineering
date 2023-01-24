package com.example.taxgo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class Vehicle extends AppCompatActivity {


    TextInputLayout VehicleClass,VehiclePurchase,OwnerStatus ;

    EditText VehiclePrice,SeatingCapacity,ImportPurchaseDate,EngineSize;
    Button Back,property_submit_Button;
    AutoCompleteTextView AutoComplete_1,AutoComplete_2,AutoComplete_3;
    AlertDialog.Builder builder;

    String server_url = "http://192.168.1.107/project/vehicle_details.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        builder = new AlertDialog.Builder(Vehicle.this);

        VehicleClass = findViewById(R.id.vehicle_class);
        VehiclePurchase = findViewById(R.id.vehicle_purchase_types);
        OwnerStatus = findViewById(R.id.owner_status);

        VehiclePrice = findViewById(R.id.vehicle_price);
        SeatingCapacity = findViewById(R.id.seating_capacity);
        ImportPurchaseDate = findViewById(R.id.purchase_date);
        EngineSize = findViewById(R.id.engine_size);


        Back = findViewById(R.id.back_button);
        Back.setOnClickListener(view -> home());

        property_submit_Button = findViewById(R.id.property_submit_button);

        AutoComplete_1 = findViewById(R.id.autocomplete_1);
        AutoComplete_2 = findViewById(R.id.autocomplete_2);
        AutoComplete_3 = findViewById(R.id.autocomplete_3);

        //We will use this data to inflate the drop-down items
        String[] vehicle_class_list = new String[]{"Car", "Bike", "Truck"};

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> vehicle_class = new ArrayAdapter<>(this, R.layout.dropdown_item, vehicle_class_list);
        AutoComplete_1.setAdapter(vehicle_class);

        //We will use this data to inflate the drop-down items
        String[] vehicle_purchase_list = new String[]{"Local","Imported"};

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> Vehicle_purchase_types = new ArrayAdapter<>(this, R.layout.dropdown_item, vehicle_purchase_list);
        AutoComplete_2.setAdapter(Vehicle_purchase_types);

        //We will use this data to inflate the drop-down items
        String[] owner_status_list = new String[]{"Owner","Not Owner"};

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> owner_status = new ArrayAdapter<>(this, R.layout.dropdown_item, owner_status_list);
        AutoComplete_3.setAdapter(owner_status);

        property_submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_in_Func();

            }
        });

    }
    public void Sign_in_Func(){

        final String f_name = VehicleClass.getEditText().getText().toString();
        final String l_name = VehiclePurchase.getEditText().getText().toString();
        final String l_name1 = OwnerStatus.getEditText().getText().toString();
        final String address = VehiclePrice.getText().toString();
        final String email = SeatingCapacity.getText().toString();
        final String password = ImportPurchaseDate.getText().toString().trim();
        final String construction_type = EngineSize.getText().toString();


        if(!email.isEmpty() && !password.isEmpty() && !f_name.isEmpty() && !l_name.isEmpty()) {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, response -> {

                builder.setTitle("Server Response");
                builder.setMessage("Response :" + response);
                builder.setPositiveButton("OK", (dialog, which) -> {

                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }

                    , error -> {
                Toast.makeText(Vehicle.this, "some error occurred .....", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> Params = new HashMap<>();
                    Params.put("Property_Type", f_name);
                    Params.put("City", l_name);
                    Params.put("Owner_Status", l_name1);
                    Params.put("Address", address);
                    Params.put("Land_Area", email);
                    Params.put("Covered_Area", password);
                    Params.put("Construction_Type", construction_type);

                    return Params;

                }
            };
            My_Singleton.getInstance(Vehicle.this).addToRequestQue(stringRequest);
        }
        else {
            Toast.makeText(Vehicle.this, "field can't be empty", Toast.LENGTH_SHORT).show();
        }
    }
    public void home(){
        finish();
    }
}