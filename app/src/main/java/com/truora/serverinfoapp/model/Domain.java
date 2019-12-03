package com.truora.serverinfoapp.model;

import java.io.Serializable;
import java.util.Date;

public class Domain implements Serializable {
    private String host;
    private InfoServer infoServer;

    public Domain() { }

    public Domain(String host, InfoServer infoServer) {
        this.host = host;
        this.infoServer = infoServer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public InfoServer getInfoServer() {
        return infoServer;
    }

    public void setInfoServer(InfoServer infoServer) {
        this.infoServer = infoServer;
    }
}
