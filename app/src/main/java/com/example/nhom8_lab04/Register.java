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

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText email = findViewById(R.id.et_rg_email);
        EditText username = findViewById(R.id.et_rg_name);
        EditText password = findViewById(R.id.et_rg_pass);
        SQLiteConnector dtb = new SQLiteConnector(this);
        Button  btnConfirm=  findViewById(R.id.btn_rg_signup);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass= password.getText().toString();
                String mail= email.getText().toString();
                if(user.equals("")|| pass.equals("")|| mail.equals(""))
                    Toast.makeText(Register.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else
                {
                    String hashPass = md5(pass);
                    User person = new User();
                    person.setName(user);
                    person.setEmail(mail);
                    person.setPassword(hashPass);

                    Boolean checkUsername=dtb.checkUser(user,hashPass);
                    if(!checkUsername)
                    {
                        dtb.addUser(person);
                        Toast.makeText(Register.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(Register.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    }
                }

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