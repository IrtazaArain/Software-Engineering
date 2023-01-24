package com.example.taxgo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Appointment extends AppCompatActivity {
    EditText Name,Email,Phone,Date;
    TextInputLayout Time;

    AutoCompleteTextView AutoComplete_1;

    Button Back,Submit;

    AlertDialog.Builder builder;

    DatePickerDialog datePickerDialog;

    String server_url = "http://192.168.1.105/project/appointment.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        Name = findViewById(R.id.name_edittext);
        Email = findViewById(R.id.email_edittext);
        Phone = findViewById(R.id.phone_edittext);

        Date = findViewById(R.id.date_edittext);
        Date.setInputType(InputType.TYPE_NULL);

        Time = findViewById(R.id.time_textinputlayout);

        Submit = findViewById(R.id.submit_button);

        Back = findViewById(R.id.back_button);
        Back.setOnClickListener(view -> home());

        builder = new AlertDialog.Builder(Appointment.this);

        AutoComplete_1 = findViewById(R.id.autocomplete_1);

        //We will use this data to inflate the drop-down items
        String[] time_list = new String[]{"11:00 AM", "2:30 PM"};

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> vehicle_class = new ArrayAdapter<>(this, R.layout.dropdown_item, time_list);
        AutoComplete_1.setAdapter(vehicle_class);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_in_Func();

            }
        });

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(Appointment.this,
                        android.app.AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                Date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


    }
    public void Sign_in_Func(){

        final String f_name = Name.getText().toString();
        final String l_name = Email.getText().toString();
        final String address = Phone.getText().toString();
        final String email = Date.getText().toString();
        final String password = Time.getEditText().getText().toString();

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
                Toast.makeText(Appointment.this, "some error occurred .....", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> Params = new HashMap<>();
                    Params.put("name", f_name);
                    Params.put("email", l_name);
                    Params.put("phone_number", address);
                    Params.put("date", email);
                    Params.put("time", password);

                    return Params;

                }
            };
            My_Singleton.getInstance(Appointment.this).addToRequestQue(stringRequest);
        }
        else {
            Toast.makeText(Appointment.this, "field can't be empty", Toast.LENGTH_SHORT).show();
        }
    }
    public void home(){
        finish();
    }
}