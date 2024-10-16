package com.linkin.common.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


@Configuration
public class SwaggerConfig {
    @Value("${spring.application.name}")
    private String applicationName;

//    @Value("${spring.security.oauth2.authorizationserver.token-uri}")
//    private String tokenUrl;

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("RateItAll API")
                        .description("RateItAll sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

    /**
     * OpenAPI 配置（元信息、安全协议）
     */
//    @Bean
//    public OpenAPI apiInfo() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes(HttpHeaders.AUTHORIZATION,
//                                new SecurityScheme()
//                                        // OAuth2 授权模式
//                                        .type(SecurityScheme.Type.OAUTH2)
//                                        .name(HttpHeaders.AUTHORIZATION)
//                                        .flows(new OAuthFlows()
//                                                .password(
//                                                        new OAuthFlow()
//                                                                .tokenUrl(tokenUrl)
//                                                                .refreshUrl(tokenUrl)
//                                                )
//                                        )
//                                        // 安全模式使用Bearer令牌（即JWT）
//                                        .in(SecurityScheme.In.HEADER)
//                                        .scheme("Bearer")
//                                        .bearerFormat("JWT")
//                        )
//                )
//                // 接口全局添加 Authorization 参数
//                .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
//                // 接口信息定义
//                .info(new Info()
//                        .title("系统服务")
//                        .version("3.0.0")
//                        .description("用户、角色、菜单、部门、字典等接口")
//                        .license(new License().name("Apache 2.0")
//                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
//                );
//    }



}
