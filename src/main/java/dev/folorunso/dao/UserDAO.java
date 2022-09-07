package dev.folorunso.dao;

import dev.folorunso.models.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User findById(int id);

    User save(User user);

    User update(User user);

    User delete(int id);
}
