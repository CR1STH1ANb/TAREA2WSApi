package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
public  TextView textView;
public  Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView)findViewById(R.id.textView7);
        bundle = this.getIntent().getExtras();
        String direccion="http://uealecpeterson.net/ws/login.php?"+"usr="+bundle.getString("Nom")+"&pass="+bundle.getString("Con");
        RequestQueue RQueue = Volley.newRequestQueue(this);
        StringRequest SRequest = new StringRequest(Request.Method.GET, direccion, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                textView.setText("Respuesta WS: " +  result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                return super.getParams();
            }
        };
        RQueue.add(SRequest);
    }
}