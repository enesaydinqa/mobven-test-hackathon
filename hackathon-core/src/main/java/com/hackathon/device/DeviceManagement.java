package com.hackathon.device;

import com.hackathon.H2db.H2DBConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Configuration
public class DeviceManagement
{
    private static final ReentrantLock reentrantLock = new ReentrantLock();

    public static Device getDevice()
    {
        reentrantLock.lock();
        Device device = selectIdleDevice();
        updateRunningDevice(device);
        reentrantLock.unlock();
        return device;
    }

    private static Device selectIdleDevice()
    {
        Device availableDevice = H2DBConfiguration.embedDB.query("SELECT deviceName, version, appium_url, uid, port FROM devices WHERE status='IDLE' LIMIT 1 FOR UPDATE",
                (rs, rowNum) ->
                {
                    Device device = new Device();
                    device.setDeviceName(rs.getString("deviceName"));
                    device.setVersion(rs.getString("version"));
                    device.setAppiumUrl(rs.getString("appium_url"));
                    device.setUid(rs.getString("uid"));
                    device.setPort(rs.getInt("port"));
                    return device;
                }).get(0);
        log.info("Device : {} - {}", availableDevice.getDeviceName(), availableDevice.getUid());
        return availableDevice;
    }

    private static void updateRunningDevice(Device device)
    {
        H2DBConfiguration.embedDB.update("UPDATE devices SET status='RUNNING' WHERE deviceName = ?", device.getDeviceName());
    }

    public static void releaseDevice(String uid)
    {
        log.info("Release Device : {}", uid);
        H2DBConfiguration.embedDB.update("UPDATE devices SET status='IDLE' WHERE uid = ?", uid);
    }
}
