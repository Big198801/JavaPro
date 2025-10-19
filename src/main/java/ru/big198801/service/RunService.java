package ru.big198801.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.big198801.model.entity.Users;
import ru.big198801.repository.UsersRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RunService implements CommandLineRunner {
    private final UserService userService;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Запуск тестов методов репозитория...");
        log.info("getUserById : {}", userService.getUserById(1L).toString());
        log.info("getUserByUserName : {}", userService.getUserByUserName("Niko").toString());
        List<Users> users = usersRepository.findAll();
        log.info("findAll :");
        for (Users user : users) {
            log.info("user : {}", user.toString());
        }
    }
}
