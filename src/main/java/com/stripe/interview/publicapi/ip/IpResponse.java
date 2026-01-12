package com.stripe.interview.publicapi.ip;

public class IpResponse {

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "IpResponse{ip='" + ip + "'}";
    }
}