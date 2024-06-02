package com.example.oop_lr9;

import com.example.oop_lr9.User;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.logging.log4j.Logger;
import jakarta.servlet.RequestDispatcher;



@WebServlet("/users")
public class usercontroller extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "3103";
    private static final Logger LOGGER = Logger.getLogger(usercontroller.class.getName());


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO users (user_firstname, user_lastname, groupp, age, subject) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, request.getParameter("user_firstname"));
                statement.setString(2, request.getParameter("user_lastname"));
                statement.setString(3, request.getParameter("groupp"));
                statement.setBoolean(4, Boolean.parseBoolean(request.getParameter("age")));
                statement.setBoolean(5, Boolean.parseBoolean(request.getParameter("subject")));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при добавлении пользователя", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при добавлении пользователя");
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Users.jsp");
        requestDispatcher.forward(request, response);

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
            String usersJson = new Gson().toJson(users);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(usersJson);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при получении списка пользователей", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при получении списка пользователей");
        }
    }


    // Обработка PUT запроса для обновления
    // Метод doDelete для удаления
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(id));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при удалении пользователя с ID: " + id, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при удалении пользователя");
        }
    }

    // Метод doPut для обновления существующего цветка
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET user_firstname = ?, user_lastname = ?, groupp = ?, age = ?, subject = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, request.getParameter("user_firstname"));
                statement.setString(2, request.getParameter("user_lastname"));
                statement.setString(3, request.getParameter("groupp"));
                statement.setString(3, request.getParameter("age"));
                statement.setString(3, request.getParameter("subject"));
                statement.setInt(6, Integer.parseInt(request.getParameter("id")));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при обновлении пользователя с ID: " + request.getParameter("id"), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при обновлении пользователя");


        }
    }
}
