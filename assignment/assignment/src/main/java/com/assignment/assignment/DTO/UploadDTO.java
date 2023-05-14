package com.assignment.assignment.DTO;

import com.assignment.assignment.Entity.Device;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder(toBuilder = true)
public class UploadDTO {

    private List<Device> data;

    private Integer count;

    private String message;

    private Integer errorCode;
}
