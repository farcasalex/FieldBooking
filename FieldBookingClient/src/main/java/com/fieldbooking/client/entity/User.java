package com.fieldbooking.client.entity;

import com.fieldbooking.client.enumeration.AccountState;
import com.fieldbooking.client.enumeration.UserRole;

import java.util.List;

public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private AccountState accountState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
