package com.assignment.assignment.Service;

import com.assignment.assignment.DTO.UploadDTO;
import com.assignment.assignment.DTO.UploadDataObject;
import com.assignment.assignment.Entity.Device;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component("IDeviceService")
public interface IDeviceService {

    void uploadData(List<Device> deviceList);

    UploadDTO getAllDeviceDetails();

    Device getDeviceById(String deviceId) ;

    List<UploadDataObject> getDeviceLatLon(String deviceId, LocalDateTime startTime, LocalDateTime endTime ) ;


}
