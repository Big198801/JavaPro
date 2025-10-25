package ru.big198801.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.big198801.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "User.withProducts", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
    Optional<List<User>> findByUsernameContaining(String username);
}
