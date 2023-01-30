package com.project.reposerty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.User;

public interface UserReposerty extends JpaRepository<User, Integer>{
	Optional<User> findUserByEmail( String email);

}
