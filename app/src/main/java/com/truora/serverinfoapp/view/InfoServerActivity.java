package com.truora.serverinfoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.truora.serverinfoapp.R;
import com.truora.serverinfoapp.adapter.ServerAdapter;
import com.truora.serverinfoapp.model.InfoServer;
import com.truora.serverinfoapp.model.Server;
import com.truora.serverinfoapp.utils.Utils;

import java.util.List;

public class InfoServerActivity extends AppCompatActivity {
    private ListView list;
    private ServerAdapter serverAdapter;
    private ImageView iv_logo;
    private ImageView iv_is_down;
    private ImageView iv_servers_changed;
    private TextView tv_title;
    private TextView tv_grade;
    private TextView tv_previous_grade;
    private TextView tv_servers_changed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_server);

        InfoServer infoServer;
        if(getIntent().hasExtra("info"))
            infoServer = Utils.parseJSON(getIntent().getStringExtra("info"));
        else
            infoServer = (InfoServer) getIntent().getSerializableExtra("infoSer");
        String host = getIntent().getStringExtra("host");

        List<Server> servers = infoServer.getServers();

        list = findViewById(R.id.list_servers);
        iv_logo = findViewById(R.id.iv_logo);
        iv_is_down = findViewById(R.id.iv_is_down);
        iv_servers_changed = findViewById(R.id.iv_servers_changed);
        tv_title = findViewById(R.id.tv_title);
        tv_grade = findViewById(R.id.tv_grade);
        tv_previous_grade = findViewById(R.id.tv_previous_grade);
        tv_servers_changed = findViewById(R.id.tv_servers_changed);

        serverAdapter = new ServerAdapter(this, servers);

        list.setAdapter(serverAdapter);

        Utils.downloadImage(infoServer.getLogo(), host, iv_logo);

        if(!infoServer.isDown()){
            iv_is_down.setImageResource(android.R.drawable.presence_online);
        }
        if(!infoServer.isServersChanged()){
            iv_servers_changed.setImageResource(android.R.drawable.presence_online);
            tv_servers_changed.setText("Servers have changed in last hour");
        }
        String title=infoServer.getTitle();
        if(title.contains(" ") && title.length()>65) {
            title = title.substring(0, title.indexOf(" "));
        }
        tv_title.setText(title);
        tv_grade.setText(infoServer.getMinGrade());
        tv_previous_grade.setText(infoServer.getPreviousMinGrade());
    }
}
