package com.assignment.assignment.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadDataObject {
    private String time_stamp;
    private String device_fk_id;
    private String latitude;
    private String longitude;
    private String sts;
    private String speed;
}
