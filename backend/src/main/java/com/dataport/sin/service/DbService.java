package com.dataport.sin.service;

import com.dataport.sin.model.number.NumbersDto;
import com.dataport.sin.model.number.SingleNumberDto;
import com.dataport.sin.model.order.OrderDto;
import com.dataport.sin.model.user.LoginDto;
import com.dataport.sin.model.user.RegisterDto;
import com.dataport.sin.model.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
public class DbService {
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
        String md5Pass = DigestUtils.md5Hex(loginDto.getPassword()).toUpperCase();
        PreparedStatement stmt = conn.prepareStatement("""
                SELECT username, email, did
                FROM accounts
                WHERE username = ?
                AND password = ?
                """);
        stmt.setString(1, loginDto.getUsername());
        stmt.setString(2, md5Pass);
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            return new UserDto(result.getInt(3), result.getString(1), result.getString(2));
        } else {
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

        while (result.next()) {
            numberDto.addNumber(result.getInt(1));
        }
        return numberDto;
    }

    public void register(RegisterDto registerDto) throws SQLException {
        String md5Pass = DigestUtils.md5Hex(registerDto.getPassword()).toUpperCase();

        PreparedStatement stmt = conn.prepareStatement("""
                INSERT INTO accounts (username, password, email) VALUES (?,?,?);
                """);
        stmt.setString(1, registerDto.getUsername());
        stmt.setString(2, md5Pass);
        stmt.setString(3, registerDto.getEmail());
        stmt.executeUpdate();
    }

    public OrderDto saveOrder(OrderDto orderDto) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("""
                    INSERT INTO orders (amount, good, userId) VALUES (?, ?, ?);
                """);
        stmt.setInt(1, orderDto.getAmount());
        stmt.setString(2, orderDto.getGood());
        stmt.setInt(3, orderDto.getCustomer());
        stmt.executeUpdate();

        ResultSet ids = stmt.getGeneratedKeys();
        if(ids.next()){
            orderDto.setDid(ids.getInt(1));
        }
        return orderDto;
    }

    public List<OrderDto> getOrdersByUserId(String userId) throws SQLException {
        List<OrderDto> orderDtos = new ArrayList<>();
        String unsecureSql = "SELECT * FROM orders WHERE userId = " + userId + ";";
        ResultSet rs = conn.createStatement().executeQuery(unsecureSql);
        while (rs.next()){
            orderDtos.add(new OrderDto(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4)
            ));
        }
        return orderDtos;
    }
}
