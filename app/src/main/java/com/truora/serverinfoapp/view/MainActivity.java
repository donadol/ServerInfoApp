package com.truora.serverinfoapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.truora.serverinfoapp.R;
import com.truora.serverinfoapp.utils.Utils;


public class MainActivity extends AppCompatActivity {
    private Button btn_search;
    private Button btn_previous;
    private EditText et_domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_search = findViewById(R.id.btn_search);
        btn_previous = findViewById(R.id.btn_previous);
        et_domain = findViewById(R.id.et_domain);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String domain = et_domain.getText().toString();
                if(domain.isEmpty()){
                    Toast.makeText(MainActivity.this, "Can search for empty domain", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Utils.IsMatch(domain, Utils.regex)) {
                    Toast.makeText(MainActivity.this, "Wrong structure for domain", Toast.LENGTH_LONG).show();
                    return;
                }
                consumeRESTVolleyInfoServer(domain);
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumeRESTVolleyDomains();
            }
        });
    }
    public void consumeRESTVolleyInfoServer(final String domain){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000/"+domain;
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        String data = (String)response;
                        Intent i = new Intent(MainActivity.this, InfoServerActivity.class);
                        i.putExtra("info", data);
                        i.putExtra("host", domain);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("TAG", "Error handling rest invocation "+error.getCause());
                        Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_LONG).show();
                    } }
        );
        queue.add(req);
    }
    public void consumeRESTVolleyDomains(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000/servers";
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        String data = (String)response;
                        Intent i = new Intent(MainActivity.this, DomainsActivity.class);
                        i.putExtra("domains", data);
                        startActivity(i);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("TAG", "Error handling rest invocation "+error.getCause());
                        Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_LONG).show();
                    } }
        );
        queue.add(req);
    }
}
