package com.sms.service;

import com.sms.data.FileHandler;
import com.sms.model.User;

import java.util.List;

public class AuthService {
    private List<User> users;
    private User loggedInUser;

    public AuthService() {
        users = FileHandler.loadUsers();
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
