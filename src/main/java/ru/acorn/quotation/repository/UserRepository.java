package ru.acorn.quotation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acorn.quotation.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
