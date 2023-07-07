package ru.kata.spring.boot_security.demo.services;



import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UsersService {

    List<User> findAll();

    User findOne(long id);

    User getUserByUsername(String username);

    void save(User user);

    void update(long id, User updatedUser);

    void delete(long id);
}
