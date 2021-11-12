package com.hackathon.device;

import lombok.Data;

@Data
public class Device
{
    private String deviceName;
    private String version;
    private String appiumUrl;
    private String uid;
    private Integer port;
}
