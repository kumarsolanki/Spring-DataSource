package com.solanki.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.solanki.employee.beans.User;
import com.solanki.user.repository.UserRepository;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

}
