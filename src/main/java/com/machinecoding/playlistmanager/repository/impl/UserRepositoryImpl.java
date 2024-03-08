package com.machinecoding.playlistmanager.repository.impl;

import com.machinecoding.playlistmanager.entity.User;
import com.machinecoding.playlistmanager.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository userRepository;
    private static List<User> userList = new ArrayList<>();
    private static long userId = 1L;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepositoryImpl();
        }
        return userRepository;
    }

    @Override
    public void createUser(String name, String email) {
        User newUser = new User(userId++, name, email);
        userList.add(newUser);
    }

    @Override
    public User getUserById(Long id) {
        return userList.stream().filter(user -> user.getUserId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return userList.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public boolean removeUser(Long id) {
        return userList.removeIf(user -> user.getUserId().equals(id));
    }

    @Override
    public List<User> getUsersList() {
        return userList;
    }
}
