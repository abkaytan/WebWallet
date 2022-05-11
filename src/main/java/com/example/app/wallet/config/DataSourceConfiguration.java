package com.example.app.wallet.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "h2Datasource")
    @Primary //mysql den daha öncelikli olduğunu belirtme annotation u
    public DataSource h2Datasource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:userdb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    /*
    @Bean(name="mysqldatasource") // ==> bean ismi ile erişebilmek için beanlara isimler verilebilir
    public DataSource mysqlDatasource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        // dataSourceBuilder.url("jdbc:mysql://localhost:3306/ooptest1?characterEncoding=UTF8");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("1234");
        return dataSourceBuilder.build();
    }

     */




}
