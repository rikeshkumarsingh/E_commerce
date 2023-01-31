package com.project.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.project.model.User;
import com.project.model.role;
import com.project.reposerty.RoleReposetry;
import com.project.reposerty.UserReposerty;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	RoleReposetry roleReposetry;
	@Autowired
	UserReposerty userReposerty;
	private RedirectStrategy  redirectStrategy= new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token =(OAuth2AuthenticationToken)authentication;
		String email=token.getPrincipal().getAttributes().get("email").toString();
		if(userReposerty.findUserByEmail(email).isPresent()) {
			
		}else {
			User user =new User();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);
			List<role>roles =new ArrayList();
			roles.add(roleReposetry.findById(2).get());
			user.setRoles(roles);
			userReposerty.save(user);

		}
		redirectStrategy.sendRedirect(request, response, "/");
		
	}
	

}
