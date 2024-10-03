package com.linkin.review;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import com.linkin.common.config.SwaggerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.linkin.review.mapper")
@EntityScan(basePackages = "com.linkin.common.entity")
@EnableDiscoveryClient
@EnableMPP
@EnableFeignClients
@Import({SwaggerConfig.class})
public class ReviewApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReviewApplication.class, args);
    }
}
