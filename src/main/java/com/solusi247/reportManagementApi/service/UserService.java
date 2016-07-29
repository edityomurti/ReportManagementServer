package com.solusi247.reportManagementApi.service;

import java.util.List;

import com.solusi247.reportManagementApi.model.User;

public interface UserService {
	User findById(long id);
    User findByName(String name);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(long id);
    List<User> getAllUser(); 
    void deleteAllUsers();
    public boolean isUserExist(User user);
}
