package ru.big198801.service;

import org.springframework.stereotype.Component;
import ru.big198801.dao.UserDao;
import ru.big198801.model.Users;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserService {
    private final UserDao userDAO;

    public UserService(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public Users getUserByName(String name) throws SQLException {
       return userDAO.getUserByName(name);
    }

    public Users getUserById(Long id) throws SQLException {
        return userDAO.getUserById(id);
    }

    public void updateUser(Users user) throws SQLException {
        userDAO.updateUser(user);
    }

    public void deleteUser(Long id) throws SQLException {
        userDAO.deleteUser(id);
    }

    public void insertUser(Users user) throws SQLException {
        userDAO.createUser(user);
    }

    public List<Users> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public void deleteAllUsers() throws SQLException {
        userDAO.deleteAllUsers();
    }

    public void clearSequences() throws SQLException {
        userDAO.clearSequences();
    }
}
