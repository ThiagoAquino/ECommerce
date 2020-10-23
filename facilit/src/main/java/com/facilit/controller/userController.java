package com.facilit.controller;

import com.facilit.model.*;
import com.facilit.repository.*;
import com.facilit.service.cartService;
import com.facilit.service.userService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value = "User")
public class userController {

    @Autowired
    private userService us;

    @Autowired
    private cartService cs;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public @ResponseBody
    User userSave(@Valid @RequestBody User user) {
        user = cs.cartCreate(user);
        us.userSave(user);
        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> userList() {
        Iterable<User> users = us.userList();
        return users;
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    public User userOnly(@PathVariable("login") String login) {
        User user = us.userOnly(login);
        return user;
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.DELETE)
    public User userDelete(@PathVariable("login") String login) {
        User user = us.userDelete(login);
        return user;
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.PUT)
    public @ResponseBody
    User userUpdate(@PathVariable("login") String login, @Valid @RequestBody User user) throws Exception {
        User u = us.userUpdate(login, user);
        return u;
    }
}
