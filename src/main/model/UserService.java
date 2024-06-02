package com.example.oop_lr9;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "3103";

    public static void saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO users (user_firstname, user_lastname, groupp, age, subject) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getuser_firstname());
                statement.setString(2, user.getuser_lastname());
                statement.setString(3, user.getgroupp());
                statement.setString(3, user.getage());
                statement.setString(3, user.getsubject());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setuser_firstname(resultSet.getString("user_firstname"));
                    user.setuser_lastname(resultSet.getString("user_lastname"));
                    user.setgroupp(resultSet.getString("groupp"));
                    user.setage(resultSet.getString("age"));
                    user.setsubject(resultSet.getString("subject"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void updateUser(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET user_firstname = ?, user_lastname = ?, groupp = ?, age = ?, subject = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getuser_firstname());
                statement.setString(2, user.getuser_lastname());
                statement.setString(3, user.getgroupp());
                statement.setString(4, user.getage());
                statement.setString(5, user.getsubject());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
