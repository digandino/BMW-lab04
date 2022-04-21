package com.example.nhom8_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.Log;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Log.d("Android Mobile", "Hello World");
        Log.i("msg","===onCreate===");
        StrictMode.setThreadPolicy(new Builder().permitAll().build());
        String url = "https://inseclab.uit.edu.vn/robots.txt";
        StringBuilder url_holder = new StringBuilder();
        url_holder.append(url);
        URLConnection conn = null;
        try {
            conn = new URL(url_holder.toString()).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setUseCaches(false);
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String response;
        String data_from_stream;
        for (response = new String(); true; response += data_from_stream) {
            String stream = null;
            try {
                stream = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            data_from_stream = stream;
            if (stream == null) {
                break;
            }
        }
        Log.i("InsecLab ", response);

    }

    @Override
    protected void onStart() {
        super.onStart();
       // Log.i("msg","===onStart===");
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  Log.i("msg","===onStop===");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  Log.i("msg","===onDestroy===");

    }

    @Override
    protected void onResume() {
        super.onResume();
       // Log.i("msg","===onResume===");

    }

    @Override
    protected void onPause() {
        super.onPause();
       // Log.i("msg","===onPause===");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
      //  Log.i("msg","===onRestart===");

    }
}