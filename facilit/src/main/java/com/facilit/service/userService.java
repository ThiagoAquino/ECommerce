package com.facilit.service;

import com.facilit.model.User;
import com.facilit.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private userRepository ur;

    @Autowired
    private cartService cs;

    public User userSave(User user) {
        user = cs.cartCreate(user);
        ur.save(user);
        return user;
    }

    public Iterable<User> userList() {
        Iterable<User> users = ur.findAll();
        return users;
    }

    public User userOnly(String login) {
            User user = ur.findByLogin(login);
            return user;
    }

    public User userDelete(String login) {
            User user = ur.findByLogin(login);
            ur.delete(user);
            return user;
    }

    public User userUpdate(String login, User user) {
            User u = ur.findByLogin(login);
            u.setLogin(user.getLogin());
            u.setPassword(user.getPassword());
            ur.save(u);
            return u;
    }

}
