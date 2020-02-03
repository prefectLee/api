package com.lee.api.vo;

public class WSRouter {

    private Integer id;

    private String path;

    private String url;

    private String limiterRate;

    private String limiterCapacity;

    private String enabled;

    private Integer StripPrefix;

    private String routeOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLimiterRate() {
        return limiterRate;
    }

    public void setLimiterRate(String limiterRate) {
        this.limiterRate = limiterRate;
    }

    public String getLimiterCapacity() {
        return limiterCapacity;
    }

    public void setLimiterCapacity(String limiterCapacity) {
        this.limiterCapacity = limiterCapacity;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Integer getStripPrefix() {
        return StripPrefix;
    }

    public void setStripPrefix(Integer stripPrefix) {
        StripPrefix = stripPrefix;
    }

    public String getRouteOrder() {
        return routeOrder;
    }

    public void setRouteOrder(String routeOrder) {
        this.routeOrder = routeOrder;
    }

    @Override
    public String toString() {
        return "WSRouter{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                ", limiterRate='" + limiterRate + '\'' +
                ", limiterCapacity='" + limiterCapacity + '\'' +
                ", enabled='" + enabled + '\'' +
                ", StripPrefix=" + StripPrefix +
                ", routeOrder='" + routeOrder + '\'' +
                '}';
    }
}
