package com.truora.serverinfoapp.utils;

import android.util.Log;

import com.truora.serverinfoapp.model.InfoServer;
import com.truora.serverinfoapp.model.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static InfoServer parseJSON(String data) {
        InfoServer infoServer = new InfoServer();
        try {
            JSONObject jsonObject = new JSONObject(data);
            infoServer.setServersChanged(jsonObject.getBoolean("servers_changed"));
            infoServer.setMinGrade(new String(jsonObject.getString("ssl_grade").getBytes("ISO-8859-1"), "UTF-8"));
            infoServer.setPreviousMinGrade(new String(jsonObject.getString("previous_ssl_grade").getBytes("ISO-8859-1"), "UTF-8"));
            infoServer.setLogo(new String(jsonObject.getString("logo").getBytes("ISO-8859-1"), "UTF-8"));
            infoServer.setTitle(new String(jsonObject.getString("title").getBytes("ISO-8859-1"), "UTF-8"));
            infoServer.setDown(jsonObject.getBoolean("is_down"));

            List<Server> serversAux = new ArrayList<>();
            JSONArray servers = jsonObject.getJSONArray("servers");
            for(int i=0;i<servers.length();++i) {
                JSONObject server = servers.getJSONObject(i);
                Log.i("SERVER", server.toString());
                serversAux.add(new Server(new String(server.getString("address").getBytes("ISO-8859-1"), "UTF-8"),
                        new String(jsonObject.getString("ssl_grade").getBytes("ISO-8859-1"), "UTF-8"),
                        new String(jsonObject.getString("country").getBytes("ISO-8859-1"), "UTF-8"),
                        new String(jsonObject.getString("owner").getBytes("ISO-8859-1"), "UTF-8")));
            }
            infoServer.setServers(serversAux);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return infoServer;
    }
}
