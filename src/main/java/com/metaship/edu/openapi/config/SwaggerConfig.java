package com.metaship.edu.openapi.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)//
                .select()//
                .apis(RequestHandlerSelectors.any())//
                .paths(Predicates.not(PathSelectors.regex("/error")))//
                .build()//
                .apiInfo(metadata())//
                .useDefaultResponseMessages(false)//
                .securitySchemes(new ArrayList<>(Arrays.asList(
                        new ApiKey("Bearer %token", "Authorization", "Header")
                )))//
                .tags(new Tag("accounts", "账户相关接口"))//
                .tags(new Tag("orders", "订单相关接口"))//
                .tags(new Tag("settlements", "结算相关接口"))//
                .tags(new Tag("jwt", "jwt调试接口"))//
                .tags(new Tag("resources", "资源相关接口"))//
                .tags(new Tag("ping", "Just a ping"))//
                .genericModelSubstitutes(Optional.class);

    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()//
                .title("《文将》测试环境 Open API Version 0.1.0")//
                .description(
                        " (1) POST /accounts/login 验证或创建用户。<br>" +
                        " (2) POST /orders 同步订单。<br>" +
                        " (3) GET /settlements 获取结算数据。<br>" +
                        " (4) 在页面测试接口前，右上角Authorize输入Bearer ${JWT}, JWT为根据约定使用相关参数和秘钥生成。<br><br>" +
                        " 注：有什么缺失和需要调整的，随时沟通。"
                )//
                .version("0.1.0")//
                .contact(new Contact("学者有师教育咨询（北京）有限公司", null, "app@xuezheyoushi.com"))//
                .build();
    }
}
