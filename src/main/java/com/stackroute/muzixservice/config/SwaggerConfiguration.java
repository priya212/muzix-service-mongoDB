package com.stackroute.muzixservice.config;

import com.stackroute.muzixservice.controller.MuzixController;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = MuzixController.class)
@Configuration
public class SwaggerConfiguration {
    private static final  String SWAGGER_API_VERSION="1.0";
    private static final  String LICENSE_TEXT="License";
    private static final  String title="Muzix REST API";
    private static final String description="RESTful API for Muzix";

    private ApiInfo apiInfo()
    {
        return  new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }

    @Bean
    public Docket muzixApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }


}
