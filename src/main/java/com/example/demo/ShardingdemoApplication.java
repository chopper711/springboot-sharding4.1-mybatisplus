package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Chopper
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.module.dao"})
public class ShardingdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingdemoApplication.class, args);
    }

}
