package com.facilit.controller;

import com.facilit.model.*;
import com.facilit.repository.*;
import com.facilit.service.cartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value = "User")
public class userController {

    @Autowired
    private userRepository ur;

    @Autowired
    private cartService cs;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public @ResponseBody
    User userSave(@Valid @RequestBody User user) throws Exception {
        try {
            user = cs.cartCreate(user);
            ur.save(user);
        } catch (Exception e) {
            throw new Exception("Please, check the user");
        }

        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> userList() {
        Iterable<User> users = ur.findAll();
        return users;
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    public User userOnly(@PathVariable("login") String login) throws Exception {
        try {
            User user = ur.findByLogin(login);
            return user;
        } catch (Exception e) {
            throw new Exception("Check the login");
        }
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.DELETE)
    public User userDelete(@PathVariable("login") String login) throws Exception {
        try {
            User user = ur.findByLogin(login);
            ur.delete(user);
            return user;
        } catch (Exception e) {
           throw new Exception("Check the login");
        }
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.PUT)
    public @ResponseBody
    User userUpdate(@PathVariable("login") String login,@Valid @RequestBody User user) throws Exception {
        try {
            User u = ur.findByLogin(login);
            u.setLogin(user.getLogin());
            u.setPassword(user.getPassword());
            ur.save(u);
            return u;
        } catch (Exception e) {
            throw new Exception("Check the login");
        }
    }
}
