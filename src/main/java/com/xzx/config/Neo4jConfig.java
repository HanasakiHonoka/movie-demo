package com.xzx.config;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfig {

    @Value("${spring.data.neo4j.uri}")
    private String uri;
    @Value("${spring.data.neo4j.username}")
    private String userName;
    @Value("${spring.data.neo4j.password}")
    private String password;

    @Bean(name = "driver")
    public Driver initDriver() {

        Driver driver;
        driver = GraphDatabase.driver(uri, AuthTokens.basic(userName, password));
        //driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic(userName, password));
        return driver;
    }

}
