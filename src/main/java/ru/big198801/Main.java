package ru.big198801;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.big198801.model.Users;
import ru.big198801.service.UserService;

import java.sql.SQLException;

@ComponentScan
@Configuration
public class Main {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        UserService userService = context.getBean(UserService.class);
        userService.clearSequences();
        userService.deleteAllUsers();
        userService.insertUser(new Users(1L, "Николай"));
        userService.insertUser(new Users(2L, "Анна"));
        userService.insertUser(new Users(3L, "Валерия"));
        userService.insertUser(new Users(4L, "Соня"));
        Users user1 = userService.getUserByName("Николай");
        Users user2 = userService.getUserByName("Анна");
        Users user3 = userService.getUserByName("Валерия");
        Users user4 = userService.getUserByName("Соня");
        System.out.println(userService.getUserById(user1.getId()));
        System.out.println(userService.getUserById(user2.getId()));
        System.out.println(userService.getUserById(user3.getId()));
        System.out.println(userService.getUserById(user4.getId()));
        userService.updateUser(new Users(user1.getId(), "Николай Афанасьев"));
        System.out.println(userService.getUserById(user1.getId()));
        userService.deleteUser(user4.getId());
        userService.getAllUsers().forEach(System.out::println);
        context.close();

    }
}