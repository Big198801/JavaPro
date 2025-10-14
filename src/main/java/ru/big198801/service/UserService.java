package ru.big198801.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.big198801.entity.Users;
import ru.big198801.repository.UsersRepository;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;


    public Users getUserByUserName(String name) throws SQLException {
        return usersRepository.findByUsername(name).orElseThrow(() -> new SQLException("No user found with name: " + name));
    }

    public Users getUserById(Long id) throws SQLException {
        return usersRepository.findById(id).orElseThrow(() -> new SQLException("No user found by id: " + id));
    }

    public void updateUser(Users user) throws SQLException {
        usersRepository.save(user);
    }

    public void deleteUserById(Long id) throws SQLException {
        usersRepository.deleteById(id);
    }

    public List<Users> getAllUsers() throws SQLException {
        return usersRepository.findAll();
    }

    public void deleteAllUsers() throws SQLException {
        usersRepository.deleteAll();
    }
}
