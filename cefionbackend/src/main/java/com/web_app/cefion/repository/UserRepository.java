package com.web_app.cefion.repository;

import com.web_app.cefion.model.user.User;
import com.web_app.cefion.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<List<User>> findUsersByRole(Role role);
    Optional<List<User>> findUsersByUsernameLike(String username);
}
