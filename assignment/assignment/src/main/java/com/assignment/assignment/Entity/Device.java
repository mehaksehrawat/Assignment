package com.assignment.assignment.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@RedisHash("Device")
public class Device implements Serializable{
    @Id
    private String time_stamp;
    private String device_fk_id;
    private String latitude;
    private String longitude;
    private String sts;
    private String speed;
}

