package com.truora.serverinfoapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.truora.serverinfoapp.R;


public class MainActivity extends AppCompatActivity {
    private Button btn_search;
    private Button btn_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_search = findViewById(R.id.btn_search);
        btn_previous = findViewById(R.id.btn_previous);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //algo
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //algo
            }
        });
    }
    public void consumeRESTVolleyInfoServer(String domain){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://localhost:3000/"+domain;
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        String data = (String)response;
                        Intent i = new Intent(MainActivity.this, DomainsActivity.class);
                        i.putExtra("domain", data);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("TAG", "Error handling rest invocation"+error.getCause());
                    } }
        );
        queue.add(req);
    }
}
