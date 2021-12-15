package com.atjh.sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ComponentScan(basePackages = {"com.atjh"}) // swagger扫描路径
@CrossOrigin
public class SqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlApplication.class, args);
    }

//    ConstantPropertiesUtil爆红
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//
//        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//
//        c.setIgnoreUnresolvablePlaceholders(true);
//
//        return c;
//    }


}
