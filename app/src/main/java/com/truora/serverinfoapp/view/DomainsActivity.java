package com.truora.serverinfoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.truora.serverinfoapp.R;
import com.truora.serverinfoapp.adapter.DomainAdapter;
import com.truora.serverinfoapp.model.Domain;
import com.truora.serverinfoapp.model.InfoServer;
import com.truora.serverinfoapp.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DomainsActivity extends AppCompatActivity {
    private ListView list;
    private DomainAdapter domainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domains);

        List<Domain> domains = parseJSON(getIntent().getStringExtra("domains"));

        list = findViewById(R.id.list);

        domainAdapter = new DomainAdapter(this, domains);

        list.setAdapter(domainAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DomainsActivity.this, InfoServerActivity.class);
                i.putExtra("info", domainAdapter.getItem(position).getInfoServer());
                startActivity(i);
            }
        });
    }

    private List<Domain> parseJSON(String data) {
        List<Domain> domainList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray items = jsonObject.getJSONArray("items");
            for(int i=0;i<items.length();++i) {
                JSONObject domain = items.getJSONObject(i);
                JSONArray infoservers = domain.getJSONArray("infoservers");
                Log.i("INFOS", infoservers.toString());
                InfoServer infoServer = Utils.parseJSON(infoservers.get(0).toString());
                domainList.add(new Domain(new String(domain.getString("domain").getBytes("ISO-8859-1"), "UTF-8"), infoServer));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return domainList;
    }
}
