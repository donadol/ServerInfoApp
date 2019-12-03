package com.truora.serverinfoapp.model;

import java.io.Serializable;
import java.util.Date;

public class Domain implements Serializable {
    private String host;
    private Date consultedTime;
    private InfoServer infoServer;

    public Domain() { }

    public Domain(String host, Date consultedTime, InfoServer infoServer) {
        this.host = host;
        this.consultedTime = consultedTime;
        this.infoServer = infoServer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getConsultedTime() {
        return consultedTime;
    }

    public void setConsultedTime(Date consultedTime) {
        this.consultedTime = consultedTime;
    }

    public InfoServer getInfoServer() {
        return infoServer;
    }

    public void setInfoServer(InfoServer infoServer) {
        this.infoServer = infoServer;
    }
}
