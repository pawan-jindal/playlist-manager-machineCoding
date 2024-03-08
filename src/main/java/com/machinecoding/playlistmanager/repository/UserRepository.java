package com.machinecoding.playlistmanager.repository;

import com.machinecoding.playlistmanager.entity.User;

import java.util.List;

public interface UserRepository {
    void createUser(String name, String email);
    User getUserById(Long id);
    User getUserByName(String name);
    boolean removeUser(Long id);
    List<User> getUsersList();
}
