package com.example.nhom8_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //declared
        EditText username =  findViewById(R.id.et_lg_username);
        EditText password =  findViewById(R.id.et_lg_pass);
        SQLiteConnector dtb = new SQLiteConnector(this);
        //Login
        Button btnLogin =  findViewById(R.id.btn_lg_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass= password.getText().toString();

                if(user.equals("")|| pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else
                {
                    String hashPass = md5(pass);
                    Boolean checkUsername=dtb.checkUser(user,hashPass);
                    if(checkUsername)
                    {
                        Toast.makeText(Login.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Display.class);
                        intent.putExtra("username",user);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        //Sign up
        Button btnSignup =  findViewById(R.id.btn_lg_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
