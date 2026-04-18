package com.rikdev.crud.services;

import com.rikdev.crud.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    List<User> getUsers(); // obtener todas las personas

    Optional<User> getUserById(Long id);

    void deleteUser(Long id);

    List<User> searchByDescription(String keyword);

    void updateUserStatus(Long id, boolean isActive);
}
