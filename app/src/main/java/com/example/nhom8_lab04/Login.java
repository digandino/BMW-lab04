package com.example.nhom8_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //declared
        EditText username =  findViewById(R.id.et_username);
        EditText password =  findViewById(R.id.et_pass);
        SQLiteConnector dtb = new SQLiteConnector(this);

        //register
        Button btnSignup =  findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass= password.getText().toString();

                if(user.equals("")|| pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else
                {
                    Boolean checkUsername=dtb.checkUser(user,pass);
                    if(checkUsername)
                    {
                        Toast.makeText(Login.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Register.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        //login
        Button btnLogin =  findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, display.class);
                startActivity(intent);
            }
        });
    }
}
