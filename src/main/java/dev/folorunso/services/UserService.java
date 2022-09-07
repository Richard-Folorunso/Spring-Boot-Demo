package dev.folorunso.services;

import dev.folorunso.models.User;
import dev.folorunso.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }
    public User getById(int id) {
        return userDAO.findById(id);
    }

    public boolean addUser(User user) {
        return (userDAO.save(user) != null);
    }

    public User updateUser(User user) {
        return userDAO.update(user);
    }

    public User deleteUserById(int id) {
        return userDAO.delete(id);
    }
}
