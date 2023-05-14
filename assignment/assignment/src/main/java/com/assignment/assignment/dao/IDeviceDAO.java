package com.assignment.assignment.dao;

import com.assignment.assignment.Entity.Device;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("iDeviceDAO")
public interface IDeviceDAO {

    boolean add(Device d);

    Device getDeviceById(String device_fk_id);

    List<Device> getAllDevice();

}
