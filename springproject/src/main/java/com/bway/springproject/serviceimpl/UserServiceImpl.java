package com.bway.springproject.serviceimpl;

import org.springframework.stereotype.Service;

import com.bway.springproject.model.User;
import com.bway.springproject.repository.UserRepository;
import com.bway.springproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepo;

	UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public void userSignup(User user) {
		userRepo.save(user);
	}

	@Override
	public User userLogin(String un, String pwd) {
		User dbuser = userRepo.findByUsernameAndPassword(un, pwd);
		return dbuser;
	}

}
