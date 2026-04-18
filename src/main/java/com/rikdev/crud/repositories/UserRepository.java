package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM device WHERE LOWER(description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<User> searchByDescription(@Param("query") String query);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email_login = :email")
    Optional<User> findByEmailLogin(@Param("email") String email);
}
