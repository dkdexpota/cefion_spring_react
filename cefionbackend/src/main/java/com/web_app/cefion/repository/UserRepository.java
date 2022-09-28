package com.web_app.cefion.repository;

import com.web_app.cefion.model.user.Permission;
import com.web_app.cefion.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<List<User>> findUsersByRole(Permission permission);
    Page<User> findAll(Pageable pageable);
    List<User> findUsersByUsernameStartingWith(String username, Pageable pageable);
    Optional<List<User>> findAllByRoleIn(List<Integer> roles);
}
