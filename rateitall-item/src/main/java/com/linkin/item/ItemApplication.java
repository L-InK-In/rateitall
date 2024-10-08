package com.linkin.item;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ExceptionHandler;

//, scanBasePackages="com.linkin"
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.linkin.item.mapper")
@EntityScan(basePackages = "com.linkin.item.pojo.entity")
@EnableDiscoveryClient
@Import({SwaggerConfig.class, MyMetaObjectHandler.class})
@EnableFeignClients
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }

}
