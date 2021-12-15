package com.atjh.sql.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Api(description = "")
@Configuration
@EnableTransactionManagement
@MapperScan("com.atjh.sql.mapper")
public class MyBatisPlusConfig {
    /**
     * 逻辑删除插件
     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }



}
