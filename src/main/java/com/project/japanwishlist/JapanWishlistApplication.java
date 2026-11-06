package com.project.japanwishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.project.japanwishlist.mapper")
public class JapanWishlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(JapanWishlistApplication.class, args);
    }
}
