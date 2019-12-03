package co.edu.javeriana.whoisapp.model;

import java.util.List;

public class InfoServer {
    private boolean serversChanged;
    private String minGrade;
    private String previousMinGrade;
    private String logo;
    private String title;
    private boolean isDown;
    private List<Server> Servers;

    public InfoServer() { }

    public InfoServer(boolean serversChanged, String minGrade, String previousMinGrade, String logo, String title, boolean isDown, List<Server> servers) {
        this.serversChanged = serversChanged;
        this.minGrade = minGrade;
        this.previousMinGrade = previousMinGrade;
        this.logo = logo;
        this.title = title;
        this.isDown = isDown;
        Servers = servers;
    }

    public boolean isServersChanged() {
        return serversChanged;
    }

    public void setServersChanged(boolean serversChanged) {
        this.serversChanged = serversChanged;
    }

    public String getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
    }

    public String getPreviousMinGrade() {
        return previousMinGrade;
    }

    public void setPreviousMinGrade(String previousMinGrade) {
        this.previousMinGrade = previousMinGrade;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public List<Server> getServers() {
        return Servers;
    }

    public void setServers(List<Server> servers) {
        Servers = servers;
    }
}
