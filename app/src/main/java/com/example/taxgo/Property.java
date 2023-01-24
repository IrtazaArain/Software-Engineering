package com.example.taxgo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class Property extends AppCompatActivity {

    TextInputLayout propertyTypeInput,City;
    Button property_submit_Button;
    ImageView first_dot,second_dot,begin_line;
    EditText LandArea,CoveredArea,Address;
    TextView clearAllText,property_details_reviewing_text,property_details_successfully_added_text;
    RadioGroup construction_type_RadioGroup;
    RadioButton RCC_RadioButton,nonRCC_RadioButton;
    Button Back;

    MaterialAlertDialogBuilder builder;

    AutoCompleteTextView autoCompleteProperty, autoCompleteCity;

    RadioButton radioButton;

    String server_url = "http://192.168.1.105/project/property_details.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        Back = findViewById(R.id.back_button);
        Back.setOnClickListener(view -> home());

        final double[] Tax_Annual_rental_revenue = {0};
        final String[] item_property_type_selected = {""};
        final String[] item_city_selected = {""};
        final String[] construction_type_text = {""};
        String[] types_list = {"Commercial Plot", "Residential Flats", "Residential House"};
        String[] city_list = {"Hyderabad", "Islamabad", "Karachi", "Lahore", "Mirpurkhas",
                "Multan", "Peshawar", "Quetta", "Rawalpindi", "Sialkot"};

        propertyTypeInput = findViewById(R.id.text_propertyTypeInput);
        City = findViewById(R.id.text_CityInput);
        property_submit_Button = findViewById(R.id.property_submit_button);
        ImageView first_dot =  findViewById(R.id.first_dot);
        ImageView second_dot =  findViewById(R.id.second_dot);
        ImageView begin_line =  findViewById(R.id.begin_line);
        Address =  findViewById(R.id.address_editText);
        LandArea =  findViewById(R.id.land_area_editText);
        CoveredArea =  findViewById(R.id.covered_area_editText);
        TextView clearAllText =  findViewById(R.id.clear_fields);
        TextView property_details_reviewing_text =  findViewById(R.id.property_details_reviewing_text);
        TextView property_details_successfully_added_text=  findViewById(R.id.property_details_successfully_added_text);
        clearAllText.setPaintFlags(clearAllText.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);


        RadioGroup construction_type_RadioGroup =  findViewById(R.id.construction_type_radioGroup);
        RCC_RadioButton =  findViewById(R.id.RCC);
        nonRCC_RadioButton =  findViewById(R.id.nonRCC);

        autoCompleteProperty =  findViewById(R.id.autoCompleteProperty);
        autoCompleteCity =  findViewById(R.id.autoCompleteCity);

        ArrayAdapter<String> propTypesAdapter = new ArrayAdapter<>(Property.this, R.layout.dropdown_item, types_list);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(Property.this, R.layout.dropdown_item, city_list);

        autoCompleteProperty.setAdapter(propTypesAdapter);
        autoCompleteCity.setAdapter(cityAdapter);

        autoCompleteProperty.setOnFocusChangeListener(new  View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true){
                    int color = getResources().getColor(R.color.Green);
                    propertyTypeInput.setEndIconTintList(ColorStateList.valueOf(color));
                    propertyTypeInput.setBoxStrokeColor(color);
                }
                else{
                    int color = getResources().getColor(R.color.black);
                    propertyTypeInput.setEndIconTintList(ColorStateList.valueOf(color));
                    propertyTypeInput.setBoxStrokeColor(color);
                }
            }
        });


        autoCompleteProperty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item_property_type_selected[0] = adapterView.getItemAtPosition(i).toString();
            }
        });

        autoCompleteCity.setOnFocusChangeListener(new  View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true){
                    int color = getResources().getColor(R.color.Green);
                    City.setEndIconTintList(ColorStateList.valueOf(color));
                    City.setBoxStrokeColor(color);
                }
                else{
                    int color = getResources().getColor(R.color.black);
                    City.setEndIconTintList(ColorStateList.valueOf(color));
                    City.setBoxStrokeColor(color);
                }
            }
        });

        autoCompleteCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item_city_selected[0] = adapterView.getItemAtPosition(i).toString();
            }
        });

        construction_type_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (RCC_RadioButton.createAccessibilityNodeInfo().isChecked()){
                    construction_type_text[0] = "RCC";
                }
                if (nonRCC_RadioButton.createAccessibilityNodeInfo().isChecked()){
                    construction_type_text[0] = "nonRCC";
                }
            }
        });

        clearAllText.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = getResources().getColor(R.color.black);
                autoCompleteCity.setText("");
                Address.setText("");
                CoveredArea.setText("");
                LandArea.setText("");
                autoCompleteProperty.setText("");
                construction_type_RadioGroup.clearCheck();
                first_dot.setImageResource(R.drawable.ic_baseline_circle_grey_24);
                second_dot.setImageResource(R.drawable.ic_baseline_circle_grey_24);
                begin_line.setImageResource(R.drawable.grey_vertical_line);
                property_details_reviewing_text.setTextColor(ColorStateList.valueOf(color));
                property_details_successfully_added_text.setTextColor(ColorStateList.valueOf(color));
            }
        });

        property_submit_Button.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String landAreaText = LandArea.getText().toString();
                String coveredAreaText = CoveredArea.getText().toString();
                String addressText = Address.getText().toString();

                if (landAreaText == null || landAreaText.isEmpty() || coveredAreaText == null
                        || coveredAreaText.isEmpty() || construction_type_text[0] == ""
                        || item_city_selected[0] == "" || item_property_type_selected[0] == ""
                        || addressText == null || addressText.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),
                            "Fill out all details...",
                            Toast.LENGTH_SHORT).show();
                }
                else if (landAreaText.trim().length()>0 && coveredAreaText.trim().length()>0
                        && addressText.trim().length()>0){
                    int color = getResources().getColor(R.color.Green);
                    Thread thread = new Thread(){
                        public void run(){
                            try{
                                sleep(5000);
                                property_details_reviewing_text.setTextColor(ColorStateList.valueOf(color));
                                first_dot.setImageResource(R.drawable.ic_baseline_circle__green_24);
                                begin_line.setImageResource(R.drawable.green_vertical_line);
                                sleep(5000);
                                second_dot.setImageResource(R.drawable.ic_baseline_circle__green_24);
                                property_details_successfully_added_text.setTextColor(ColorStateList.valueOf(color));
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                            finally{
                            }
                        }
                    };thread.start();

                    if (item_property_type_selected[0].equals("Residential House")){
                        double landAreaTax = Integer.parseInt(landAreaText) * 0.5 * 12;
                        double coveredAreaTax = Integer.parseInt(coveredAreaText) * 0.5 * 12;
                        double net_GARV = landAreaTax + coveredAreaTax;
                        double allowance_for_Repair_Maintenance = net_GARV * 0.1;
                        Tax_Annual_rental_revenue[0] = net_GARV - allowance_for_Repair_Maintenance;
                    }
                    else if (item_property_type_selected[0].equals("Residential Flats")){
                        double landAreaTax = 0 * 0.6 * 12;
                        double coveredAreaTax = Integer.parseInt(coveredAreaText) * 0.6 * 12;
                        double net_GARV = landAreaTax + coveredAreaTax;
                        double allowance_for_Repair_Maintenance = net_GARV * 0.1;
                        Tax_Annual_rental_revenue[0] = net_GARV - allowance_for_Repair_Maintenance;
                    }
                    else if (item_property_type_selected[0].equals("Commercial Plot")){
                        double landAreaTax = Integer.parseInt(landAreaText) * 5 * 12;
                        double coveredAreaTax = Integer.parseInt(coveredAreaText) * 5 * 12;
                        double net_GARV = landAreaTax + coveredAreaTax;
                        double allowance_for_Repair_Maintenance = net_GARV * 0.1;
                        Tax_Annual_rental_revenue[0] = net_GARV - allowance_for_Repair_Maintenance;
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Fill out all details...",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        property_submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedID = construction_type_RadioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedID);
                Sign_in_Func();

            }
        });

    }
    public void Sign_in_Func(){

        final String f_name = propertyTypeInput.getEditText().getText().toString();
        final String l_name = City.getEditText().getText().toString();
        final String address = Address.getText().toString();
        final String email = LandArea.getText().toString();
        final String password = CoveredArea.getText().toString().trim();
        final String construction_type = radioButton.getText().toString();

        if(!email.isEmpty() && !password.isEmpty() && !f_name.isEmpty() && !l_name.isEmpty()) {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, response -> {

                builder = new MaterialAlertDialogBuilder(Property.this, R.style.MyAlertDialogStyle)

                        .setTitle("Server Response")
                        .setMessage("Response :"+response)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                autoCompleteCity.setText("");
                                Address.setText("");
                                CoveredArea.setText("");
                                LandArea.setText("");
                                autoCompleteProperty.setText("");
                                radioButton.setChecked(false);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }

                    , error -> {
                Toast.makeText(Property.this, "some error occurred .....", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> Params = new HashMap<>();
                    Params.put("Property_Type", f_name);
                    Params.put("City", l_name);
                    Params.put("Address", address);
                    Params.put("Land_Area", email);
                    Params.put("Covered_Area", password);
                    Params.put("Construction_Type", construction_type);

                    return Params;

                }
            };
            My_Singleton.getInstance(Property.this).addToRequestQue(stringRequest);
        }
        else {
            Toast.makeText(Property.this, "field can't be empty", Toast.LENGTH_SHORT).show();
        }
    }
    public void home(){
        finish();
    }
}