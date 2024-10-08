package com.linkin.user;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
//import com.linkin.common.config.AutoFillConfig;
import com.linkin.common.config.MyMetaObjectHandler;
import com.linkin.common.config.SwaggerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.linkin.user.mapper")
@EntityScan(basePackages = "com.linkin.user.pojo.entity")
@EnableDiscoveryClient
@EnableFeignClients
@Import({SwaggerConfig.class, MyMetaObjectHandler.class})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    // 注解支持的配置Bean
//    @Bean
//    public SentinelResourceAspect sentinelResourceAspect() {
//        return new SentinelResourceAspect();
//    }
}
