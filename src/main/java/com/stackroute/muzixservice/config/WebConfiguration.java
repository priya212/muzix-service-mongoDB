package com.stackroute.muzixservice.config;

/*import org.h2.server.web.WebServlet;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.stackroute.*")
public class WebConfiguration {
    /*@Bean
    ServletRegistrationBean h2ServletRegistration() {
        ServletRegistrationBean registrationBean= new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return  registrationBean;
    }*/

    @Autowired
    private Environment environment;


    @Bean("datasource")
    public DataSource dataSource()
    {
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("spring.dataSource.driverClassName"));
        driverManagerDataSource.setUrl(environment.getProperty("spring.dataSource.url"));
        driverManagerDataSource.setUsername(environment.getProperty("spring.dataSource.userName"));
        driverManagerDataSource.setPassword(environment.getProperty("spring.dataSource.password"));
        return  driverManagerDataSource;
    }

}
