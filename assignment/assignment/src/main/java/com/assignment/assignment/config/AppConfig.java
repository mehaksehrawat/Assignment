package com.assignment.assignment.config;

import com.assignment.assignment.Entity.Device;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfig{
    @Bean
    public RedisConnectionFactory cf(){
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Device> rt(){
        RedisTemplate<String, Device> template = new RedisTemplate<>();
        template.setConnectionFactory(cf());
        return template;
    }
}
