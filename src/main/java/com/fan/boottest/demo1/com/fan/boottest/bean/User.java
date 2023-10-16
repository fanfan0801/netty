package com.fan.boottest.demo1.com.fan.boottest.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "user")
@Component
@Data
@ToString
public class User {
    private String username;
    private Integer age;
    private String hobby;

}
