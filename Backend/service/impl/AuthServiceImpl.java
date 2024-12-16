package com.samilemir.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.samilemir.dto.DtoUser;
import com.samilemir.jwt.AuthRequest;
import com.samilemir.model.User;
import com.samilemir.repos.UserRepos;
import com.samilemir.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {
	
	@Autowired
	private UserRepos userRepos;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private User createUser(AuthRequest request) {
		User user=new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		return user;
	}
	
	
	@Override
	public DtoUser register(AuthRequest request) {
		DtoUser dto=new DtoUser();

		User savedUser=userRepos.save(createUser(request));
		BeanUtils.copyProperties(savedUser, dto);
		return dto;
	}

}
