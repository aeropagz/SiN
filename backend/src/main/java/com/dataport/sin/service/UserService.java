package com.dataport.sin.service;

import com.dataport.sin.model.LoginDto;
import com.dataport.sin.model.NumbersDto;
import com.dataport.sin.model.SingleNumberDto;
import com.dataport.sin.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
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


    public void initDb() {
        if (conn != null)
            return;

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

    public UserDto login(LoginDto loginDto) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("""
                SELECT username, email
                FROM accounts
                WHERE username = ?
                AND password = ?
                """);
        stmt.setString(1, loginDto.getUsername());
        stmt.setString(2, loginDto.getPassword());
        ResultSet result = stmt.executeQuery();
        if (result.next()){
            return new UserDto(result.getString(1), result.getString(2));
        }
        else{
            return null;
        }
    }

    public NumbersDto saveNumber(SingleNumberDto number) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("""
            INSERT INTO numbers (number) VALUES (?);
        """);
        stmt.setInt(1, number.getNumber());
        stmt.executeUpdate();
        PreparedStatement allStmt = conn.prepareStatement("SELECT number FROM numbers");
        ResultSet result = allStmt.executeQuery();
        NumbersDto numberDto = new NumbersDto();

        while(result.next()){
            numberDto.addNumber(result.getInt(1));
        }
        return numberDto;
    }
}
