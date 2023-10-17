package ru.clevertec.testing.unit.service;


import lombok.AllArgsConstructor;
import ru.clevertec.testing.unit.model.User;
import ru.clevertec.testing.unit.repository.UserRepository;
import ru.clevertec.testing.unit.repository.UserRepositoryFinal;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User add(User user) {
        return userRepository.add(user);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public User getById(UUID id) {
        return userRepository.getById(id);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

}
