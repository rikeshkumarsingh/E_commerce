package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.model.User;
import com.project.model.role;
import com.project.reposerty.RoleReposetry;
import com.project.reposerty.UserReposerty;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserReposerty userReposerty;
	@Autowired
	RoleReposetry roleReposetry;
	
	@GetMapping("/login")
	public String login(){
	return "login";
		
	}
	@GetMapping("/register")
	public String registerget(){
	return "register";
		
	}
	@PostMapping("/register")
	public String registeruser(@ModelAttribute("user") User user,HttpServletRequest request) throws ServletException {
		 String  password = user.getPassword();
		 user.setPassword(bCryptPasswordEncoder.encode(password));
		 List<role>roles =new ArrayList();
		 roles.add(roleReposetry.findById(2).get());
		user.setRoles(roles);
		userReposerty.save(user);
		request.login(user.getEmail(), password);
		return "/redirect:/";
	}
	
	
	

}
