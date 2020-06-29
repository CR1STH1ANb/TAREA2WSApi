package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea2.Retrofit.Banco;
import com.example.tarea2.Retrofit.Conexion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    public TextView txtJson;
    public EditText txtnombre, txtcontraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonBanco();
    }
    public void onclick(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        txtJson = findViewById(R.id.textView4);
        txtnombre = (EditText) findViewById(R.id.TextName);
        txtcontraseña = (EditText) findViewById(R.id.TextPassword);
        Bundle a = new Bundle();
        a.putString("Nom", txtnombre.getText().toString());
        a.putString("Con", txtcontraseña.getText().toString());
        intent.putExtras(a);
        startActivity(intent);
    }

    private void JsonBanco() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Conexion cn = retro.create(Conexion.class);
        Call<List<Banco>> call = cn.getBanco("c0cb288d986745378c5b6cfccef50953");
        call.enqueue(new Callback<List<Banco>>() {
            @Override
            public void onResponse(Call<List<Banco>> call, Response<List<Banco>> response) {
                if (!response.isSuccessful()) {
                    txtJson.setText("SERIE:" + response.code());
                    return;
                }
                List<Banco> postBanco = response.body();
                for (Banco b : postBanco) {
                    String dato = "";
                    dato += "Banco: " + b.getName() + "\n\n";
                  txtJson.append(dato);
                }
            }
            @Override
            public void onFailure(Call<List<Banco>> call, Throwable t) {
                txtJson.setText(t.getMessage());
            }
        });
    }

}