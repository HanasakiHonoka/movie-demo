package com.xzx;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.xzx.mapper")
public class MovieDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieDemoApplication.class, args);
    }

}
