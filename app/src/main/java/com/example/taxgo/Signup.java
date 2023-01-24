package com.example.taxgo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    ImageView pass_visible,UploadImg,AddImage;
    EditText F_name, L_name, CNIC, Email, Password;
    TextView Login;

    Button create_account;

    Bitmap bitmap;
    String encodedImageString;

    AlertDialog.Builder builder;

    String server_url = "http://192.168.1.105/project/signup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Login = findViewById(R.id.login_textVew);

        F_name = findViewById(R.id.firstname_edittext);
        L_name = findViewById(R.id.lastname_edittext);
        Email = findViewById(R.id.email_edittext);
        CNIC = findViewById(R.id.cnic_edittext);
        pass_visible = findViewById(R.id.show_pass_btn);
        Password = findViewById(R.id.password_edittext);

        create_account =findViewById(R.id.create_button);

        UploadImg = findViewById(R.id.profile_image);
        AddImage = findViewById(R.id.add_image);

        Login.setOnClickListener(view -> Login());
        create_account.setOnClickListener(view -> Sign_in_Func());
        pass_visible.setOnClickListener(this::ShowHidePass);
        AddImage.setOnClickListener(view -> Browse());

        builder = new AlertDialog.Builder(Signup.this, R.style.MyAlertDialogStyle);
    }

    public  void  Browse(){
        Dexter.withActivity(Signup.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            assert data != null;
            Uri filepath = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                UploadImg.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);
            }catch(Exception ex){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);

        byte[] bytes_of_image = byteArrayOutputStream.toByteArray();
        encodedImageString = android.util.Base64.encodeToString(bytes_of_image, Base64.DEFAULT);
    }

    public void Sign_in_Func(){

        final String f_name = F_name.getText().toString();
        final String l_name = L_name.getText().toString();
        final String cnic = CNIC.getText().toString();
        final String email = Email.getText().toString();
        final String password = Password.getText().toString().trim();

        if(!email.isEmpty() && !password.isEmpty() && !f_name.isEmpty() && !l_name.isEmpty() && !cnic.isEmpty()) {
            if (email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && email.length() > 0){
                if (password.length() >= 8){
                    if (cnic.length() == 13){

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, response -> {

                            builder.setTitle("Server Response");
                            builder.setMessage("Response :" + response);
                            builder.setPositiveButton("OK", (dialog, which) -> {
                                F_name.setText("");
                                L_name.setText("");
                                CNIC.setText("");
                                Email.setText("");
                                Password.setText("");
                                UploadImg.setImageResource(R.drawable.ic_user);

                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }

                                , error -> {
                            Toast.makeText(Signup.this, "some error occurred .....", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();

                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> Params = new HashMap<>();
                                Params.put("f_name", f_name);
                                Params.put("l_name", l_name);
                                Params.put("cnic", cnic);
                                Params.put("email", email);
                                Params.put("password", password);
                                Params.put("upload", encodedImageString);

                                return Params;

                            }
                        };
                        My_Singleton.getInstance(Signup.this).addToRequestQue(stringRequest);

                    }
                    else{
                        Toast.makeText(Signup.this, "Invalid cnic", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Signup.this, "Password must be at least 8 character", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(Signup.this, "Invalid email address", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(Signup.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }
    }


    public void Login(){
        Intent intent = new Intent(this, com.example.taxgo.Login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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