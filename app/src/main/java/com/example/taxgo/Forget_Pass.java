package com.example.taxgo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Forget_Pass extends AppCompatActivity {

    ImageView pass_visible;
    EditText Email, Password;
    Button Submit, Back;

    AlertDialog.Builder builder;

    String server_url = "http://192.168.1.105/project/Forget_Password.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        Submit = findViewById(R.id.submit_button);
        Back = findViewById(R.id.back_button);

        Email = findViewById(R.id.email_edittext);
        pass_visible = findViewById(R.id.show_pass_btn);
        Password = findViewById(R.id.password_edittext);

        Back.setOnClickListener(view -> Login());
        pass_visible.setOnClickListener(this::ShowHidePass);
        Submit.setOnClickListener(view -> Forget_Pass_Func());

        builder = new AlertDialog.Builder(Forget_Pass.this);
    }
    public void Forget_Pass_Func(){

        final String email = Email.getText().toString().trim();
        final String password = Password.getText().toString().trim();

        if(!email.isEmpty() && !password.isEmpty()) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    builder.setTitle("Server Response");
                    builder.setMessage("Response :"+response);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Email.setText("");
                            Password.setText("");

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }

                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Forget_Pass.this,"some error occurred .....", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map <String,String> Params = new HashMap<String, String>();
                    Params.put("email",email);
                    Params.put("password",password);
                    return Params;

                }
            };
            My_Singleton.getInstance(Forget_Pass.this).addToRequestQue(stringRequest);
        }
        else{
            Toast.makeText(Forget_Pass.this, "fields can't be empty", Toast.LENGTH_SHORT).show();
        }

    }

    public void Login(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
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