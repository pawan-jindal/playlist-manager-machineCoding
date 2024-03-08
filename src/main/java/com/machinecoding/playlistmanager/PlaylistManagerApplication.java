package com.machinecoding.playlistmanager;

import com.machinecoding.playlistmanager.entity.User;
import com.machinecoding.playlistmanager.repository.UserPlayListRepository;
import com.machinecoding.playlistmanager.repository.UserRepository;
import com.machinecoding.playlistmanager.repository.impl.UserPlayListRepositoryImpl;
import com.machinecoding.playlistmanager.repository.impl.UserRepositoryImpl;

public class PlaylistManagerApplication {

	public static void main(String[] args) {
		UserRepository userRepository = UserRepositoryImpl.getInstance();
		UserPlayListRepository userPlayListRepository =  UserPlayListRepositoryImpl.getInstance();
		// initial Users
		System.out.println("initial Users: " + userRepository.getUsersList());

		// create user 1
		userRepository.createUser("pawan", "pawan@gmail.com");
		System.out.println("after creating Users: " + userRepository.getUsersList());

		// remove user 2
		User user1 = userRepository.getUserByName("pawan");
		userRepository.removeUser(user1.getUserId());
		System.out.println("after removing Users: " + userRepository.getUsersList());

	}
}
