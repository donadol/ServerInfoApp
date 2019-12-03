package com.truora.serverinfoapp.utils;

import com.truora.serverinfoapp.model.InfoServer;
import com.truora.serverinfoapp.model.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class utils {

    private InfoServer parseJSON(String data) {
        InfoServer infoServer = new InfoServer();
        try {
            JSONObject jsonObject = new JSONObject(data);
            infoServer.setServersChanged(jsonObject.getBoolean("servers_changed"));
            infoServer.setMinGrade(jsonObject.getString("ssl_grade"));
            infoServer.setPreviousMinGrade(jsonObject.getString("previous_ssl_grade"));
            infoServer.setLogo(jsonObject.getString("logo"));
            infoServer.setTitle(jsonObject.getString("title"));
            infoServer.setDown(jsonObject.getBoolean("is_down"));

            List<Server> serversAux = new ArrayList<>();
            JSONArray servers = jsonObject.getJSONArray("servers");
            for(int i=0;i<servers.length();++i) {
                JSONObject server = servers.getJSONObject(i);
                serversAux.add(new Server(server.getString("address"), jsonObject.getString("ssl_grade"), jsonObject.getString("country"), jsonObject.getString("owner")));
            }
            infoServer.setServers(serversAux);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return infoServer;
    }
}
