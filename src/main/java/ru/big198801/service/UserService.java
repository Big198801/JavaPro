package ru.big198801.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.big198801.mapper.UsersMapper;
import ru.big198801.model.dto.UsersDto;
import ru.big198801.model.entity.Users;
import ru.big198801.repository.UsersRepository;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;


    public UsersDto getUserByUserName(String name){
        log.info("Запрос пользователя с username: {}", name);
        Users user = usersRepository.findByUsername(name).orElseThrow(() -> {
            log.error("Пользователя с username не найден: {}", name);
            return new EntityNotFoundException("Пользователь не найден username: " + name);
        });
        return usersMapper.toUsersDto(user);
    }
    @Transactional
    public UsersDto getUserById(Long id) {
        log.info("Запрос пользователя с ID: {}", id);
        Users user = usersRepository.findById(id).orElseThrow(() -> {
            log.error("Пользователя с ID не найден: {}", id);
            return new EntityNotFoundException("Пользователь не найден ID: " + id);
        });
        return usersMapper.toUsersDto(user);
    }

    @Transactional
    public void updateUser(Users user) {
        usersRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    @Transactional
    public List<UsersDto> getAllUsers() {
        return usersMapper.toUsersDtoList(usersRepository.findAll());
    }

    @Transactional
    public void deleteAllUsers() {
        usersRepository.deleteAll();
    }
}
