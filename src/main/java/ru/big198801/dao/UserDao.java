package ru.big198801.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.big198801.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    private final HikariDataSource hikariDataSource;

    @Autowired
    public UserDao(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    public void createUser(Users user) throws SQLException {
        String sql = "INSERT INTO users (username) VALUES (?)";
        try (Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.executeUpdate();
        }
    }

    public Users getUserById(Long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Users(rs.getLong("id"), rs.getString("username"));
            }
        }
        return null;
    }

    public Users getUserByName(String username) throws SQLException {
        String sql = "SELECT * FROM users where username = ?";
        try (Connection conn = hikariDataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Users(rs.getLong("id"), rs.getString("username"));
            }
        }
        return null;
    }

    public void updateUser(Users users) throws SQLException {
        String sql = "UPDATE users set username = ? where id = ?";
        try (Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, users.getUserName());
            preparedStatement.setLong(2, users.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(Long id) throws SQLException {
        String sql = "DELETE from users where id = ?";
        try (Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<Users> getAllUsers() throws SQLException {
        String sql = "SELECT * from users";
        try (Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Users> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new Users(rs.getLong("id"), rs.getString("username")));
            }
            return users;
        }
    }

    public void deleteAllUsers() throws SQLException {
        String sql = "DELETE from users";
        try (Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
    }

    public void clearSequences() throws SQLException {
        String sql = "alter sequence users_id_seq restart with 1";
        try (Connection connection = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
    }
}
