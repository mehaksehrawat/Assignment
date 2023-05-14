package com.assignment.assignment.Service;

import com.assignment.assignment.DTO.UploadDTO;
import com.assignment.assignment.DTO.UploadDataObject;
import com.assignment.assignment.Entity.Device;
import com.assignment.assignment.dao.IDeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "DeviceService")
public class DeviceServiceImp implements IDeviceService {

    @Autowired
    private IDeviceDAO iDeviceDAO;

    @Override
    public void uploadData(List<Device> deviceList) {
        deviceList.forEach(device -> iDeviceDAO.add(device));
    }

    @Override
    public UploadDTO getAllDeviceDetails() {
        UploadDTO uploadDTO = UploadDTO.builder().build();
        List<Device> deviceList = iDeviceDAO.getAllDevice();
        if (deviceList == null) {
            uploadDTO.setMessage("No record Found");
            uploadDTO.setErrorCode(-1);
        } else {
            uploadDTO.setMessage("Transaction SuccessFull");
            uploadDTO.setErrorCode(1);
            uploadDTO.setCount(deviceList.size());
            uploadDTO.setData(deviceList);
        }
        return uploadDTO;
    }

    @Override
    public Device getDeviceById(String deviceId) {
        return iDeviceDAO.getDeviceById(deviceId);
    }

    @Override
    public List<UploadDataObject> getDeviceLatLon(String deviceId, LocalDateTime startTime, LocalDateTime endTime) {
        List<UploadDataObject> list = new ArrayList<>();
        List<Device> deviceListById = iDeviceDAO.getAllDevice();

        if (!deviceListById.isEmpty()) {
            deviceListById.stream()
                    .filter(x -> x.getDevice_fk_id().equals(deviceId))
                    .forEach(device -> {
                        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        LocalDateTime timeStamp = LocalDateTime.parse(device.getTime_stamp(), sdf);

                        if (startTime != null && endTime != null){
                        if (timeStamp.isAfter(startTime) && timeStamp.isBefore(endTime)) {
                            list.add(UploadDataObject.builder()
                                    .time_stamp(device.getTime_stamp())
                                    .longitude(device.getLongitude())
                                    .latitude(device.getLatitude())
                                    .build());
                        }
                        }else {
                            list.add(UploadDataObject.builder()
                                    .time_stamp(device.getTime_stamp())
                                    .longitude(device.getLongitude())
                                    .latitude(device.getLatitude())
                                    .build());
                        }

                    });
        } else {
            throw new RuntimeException("No Data Find");
        }

        return list;
    }
}
