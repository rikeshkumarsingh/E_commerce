package com.project.reposerty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Category;

public interface CategoryReposerty extends JpaRepository<Category, Integer> {

}
