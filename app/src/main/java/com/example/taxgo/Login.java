package com.example.taxgo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    ImageView pass_visible;
    TextView Forget, SignUp;
    EditText Email, Password;
    Button LoginBtn;

    MaterialAlertDialogBuilder builder;

    public static String PREFS_NAME = "credentials";

    String server_url = "http://192.168.1.105/project/login.php";
    String profile_url = "http://192.168.1.107/project/profile.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //ClickableText
        SignUp = findViewById(R.id.sign_up_textview);
        Forget = findViewById(R.id.help_login_textview);

        //EditText
        Email = findViewById(R.id.email_edittext);
        Password = findViewById(R.id.password_edittext);
        pass_visible = findViewById(R.id.show_pass_btn);

        //Buttons
        LoginBtn = findViewById(R.id.login_button);

        //Function
        pass_visible.setOnClickListener(this::ShowHidePass);
        SignUp.setOnClickListener(view -> Signup_Func());
        Forget.setOnClickListener(view -> Forget_Func());
        LoginBtn.setOnClickListener(view -> Login_Func());


    }

    public void Login_Func(){

        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();

        if(!email.isEmpty() && !password.isEmpty()) {
            if (email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") &&email.length() > 0){
                if (password.length() >= 8){

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, response -> {

                        builder = new MaterialAlertDialogBuilder(Login.this, R.style.MyAlertDialogStyle)

                        .setTitle("Server Response")
                        .setMessage("Response :"+response)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Email.setText("");
                                Password.setText("");

                                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("useremail", Email.getText().toString());
                                editor.apply();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();

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
                        Toast.makeText(Login.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }){
                        @Override
                        protected Map<String, String> getParams() {
                            Map <String,String> Params = new HashMap<>();
                            Params.put("email",email);
                            Params.put("password",password);
                            return Params;

                        }
                    };
                    My_Singleton.getInstance(Login.this).addToRequestQue(stringRequest);

                }
                else{
                    Toast.makeText(Login.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(Login.this, "Invalid email address", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(Login.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //converting the string to json array object
                            JSONArray array = jsonObject.getJSONArray("data");

                            Toast.makeText(Login.this, "Invalid password", Toast.LENGTH_SHORT).show();

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject object = array.getJSONObject(i);

                                String name = object.getString("name");
                                String email = object.getString("email");
                                String cnic = object.getString("cnic");
                                String number = object.getString("number");


                                Intent intent = new Intent(Login.this,Profile.class);
                                intent.putExtra("name",name);
                                intent.putExtra("email",email);
                                intent.putExtra("cnic", cnic);
                                intent.putExtra("number",number);

                                Toast.makeText(Login.this, name, Toast.LENGTH_SHORT).show();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our string_request to queue
        My_Singleton.getInstance(Login.this).addToRequestQue(stringRequest);

    }

    public void Signup_Func(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public void Forget_Func(){
        Intent intent = new Intent(this, Forget_Pass.class);
        startActivity(intent);
    }

    public void ShowHidePass(View view) {
        if(view.getId()==R.id.show_pass_btn){
            if(Password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_visibility);
                //Show Password
                Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_visibility_off);
                //Hide Password
                Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            Password.setSelection(Password.getText().length());
        }
    }
}