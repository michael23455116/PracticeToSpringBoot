package com.example.ecommerce.rowmapper;

import com.example.ecommerce.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreatedDate(rs.getDate("createdDate"));
        user.setLastModifiedDate(rs.getDate("lastModifiedDate"));
        return user;
    }
}
