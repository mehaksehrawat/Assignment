package com.assignment.assignment.dao;

import com.assignment.assignment.Entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class DeviceDAOImpl implements IDeviceDAO{

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY= "DEVICE";
    @Override
    public boolean add(Device d) {
        try{
            redisTemplate.opsForHash().put(KEY,d.getTime_stamp(),d);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Device getDeviceById(String device_fk_id) {
        Device device=(Device) redisTemplate.opsForHash().get(KEY,device_fk_id);
        return device;
    }

    @Override
    public List<Device> getAllDevice() {
        List<Device> deviceList = redisTemplate.opsForHash().values(KEY);
        return deviceList;
    }

}
