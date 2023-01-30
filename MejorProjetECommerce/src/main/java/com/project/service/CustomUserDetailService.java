package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.model.CustomUserDetail;
import com.project.model.User;
import com.project.reposerty.UserReposerty;

@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	UserReposerty userReposerty;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user=userReposerty.findUserByEmail(username);
		user.orElseThrow(()-> new UsernameNotFoundException("user not fount"));
		return user.map(CustomUserDetail::new ).get();
	}
	

}
