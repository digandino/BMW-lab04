package com.example.nhom8_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText email = findViewById(R.id.et_email);
        EditText username = findViewById(R.id.et_username);
        EditText password = findViewById(R.id.et_pass);
        SQLiteConnector dtb = new SQLiteConnector(this);

        Button  btnConfirm=  findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass= password.getText().toString();
                String mail= email.getText().toString();

                User person = new User();
                person.setName(user);
                person.setEmail(mail);
                person.setPassword(pass);

                if(user.equals("")|| pass.equals("")|| mail.equals(""))
                    Toast.makeText(Register.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else
                {
                    Boolean checkUsername=dtb.checkUser(user,pass);
                    if(!checkUsername)
                    {
                        dtb.addUser(person);
                        Toast.makeText(Register.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Register.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }
}