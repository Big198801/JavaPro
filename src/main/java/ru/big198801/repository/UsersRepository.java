package ru.big198801.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.big198801.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    void deleteByUsername(String username);

    Optional<List<Users>> findByUsernameContaining(String username);
}
