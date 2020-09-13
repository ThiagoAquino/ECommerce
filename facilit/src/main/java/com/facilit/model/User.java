package com.facilit.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements Serializable, UserDetails {

    @Id
    @Column(unique = true, nullable =  false)
    private String login;

    @NotBlank
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "login"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "nameRole"))
    private List<Role> roles;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(){}

    public User(String username, String password, boolean b, boolean b1, boolean b2, boolean b3, Collection<? extends GrantedAuthority> authorities) { }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
