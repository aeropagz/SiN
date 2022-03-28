package com.dataport.sin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
@Slf4j
public class UserService {
    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String db_user;
    @Value("${spring.datasource.password}")
    private String db_pass;

    private Connection conn;

    public UserService() {

    }


    public void initDb() {
        Properties props = new Properties();
        props.setProperty("user", db_user);
        props.setProperty("password", db_pass);
        try {
            conn = DriverManager.getConnection(db_url, props);
            log.info("Database Connection establish.");
        } catch (SQLException e) {
            log.error("Database Connection could not been established: " + e.getMessage());
        }
    }
}
