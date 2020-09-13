package com.facilit.security;

import com.facilit.model.User;
import com.facilit.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;


@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

    @Autowired
    private userRepository ur;

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = ur.findByLogin(login);
        User user1 = new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
        return user1;
    }

}
