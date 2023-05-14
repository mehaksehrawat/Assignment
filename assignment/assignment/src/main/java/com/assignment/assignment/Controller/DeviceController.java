package com.assignment.assignment.Controller;

import com.assignment.assignment.DTO.UploadDTO;
import com.assignment.assignment.DTO.UploadDataObject;
import com.assignment.assignment.Entity.Device;
import com.assignment.assignment.Service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private IDeviceService iDeviceService;

    @GetMapping(value = "/uploadData")
    public UploadDTO uploadData(){
        File fileName= new File("/Users/mohitrathi/Downloads/assignment/src/main/resources/templates/RawData.csv");
        String line="";
        String dleimeter =",";
        int iterator=0;
        List<Device> deviceList=new ArrayList<Device>();
        try{
            BufferedReader reader= new BufferedReader(new FileReader(fileName));
            while((line =reader.readLine())!=null){
                if(iterator==0){
                    iterator++;
                    continue;
                }
                String[] data=line.split(dleimeter);
                deviceList.add(Device.builder()
                        .device_fk_id(data[0])
                                .latitude(data[1])
                                .longitude(data[2])
                                .time_stamp(data[3])
                                .sts(data[4])
                                .speed(data[5])
                        .build());
            }
            iDeviceService.uploadData(deviceList);
            return UploadDTO.builder()
                    .message("Transaction Successful")
                    .errorCode(1)
                    .count(deviceList.size())
                    .build();

        }catch (Exception e){
            throw new RuntimeException("Exception:",e);
        }
    }

    @GetMapping ("/getAllDeviceData")
    public UploadDTO getAllDeviceDetails(){
        return iDeviceService.getAllDeviceDetails();
    }

    @GetMapping ("/getDeviceById")
    public Device getDeviceById(@RequestParam(name = "deviceId") String deviceId){
      return iDeviceService.getDeviceById(deviceId);
    }

    @GetMapping ("/getDeviceLatLon")
    public List<UploadDataObject> getDeviceLatLon(@RequestParam(name = "deviceId") String deviceId,
                                            @RequestParam(name = "startTime", required = false) String startTime,
                                            @RequestParam(name = "endTime", required = false) String endTime){

        LocalDateTime startDate= null;
        LocalDateTime endDate = null;

        if(StringUtils.hasText(startTime) || StringUtils.hasText(endTime)) {
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                startDate = LocalDateTime.parse(startTime, sdf);
                endDate = LocalDateTime.parse(endTime, sdf);
        }

        return iDeviceService.getDeviceLatLon(deviceId, startDate, endDate);
    }



}
