package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Category;
import com.project.reposerty.CategoryReposerty;

@Service
public class CategoriesService {
	@Autowired
	CategoryReposerty categoryReposerty;
	public List<Category> getAllCategories(){
		return categoryReposerty.findAll();
	}
	public void addCategories(Category category) {
		categoryReposerty.save(category);
	}
	public void removeCategoryById(int id) {
		categoryReposerty.deleteById(id);
		
	}
	public Optional<Category> update(int id) {
		return categoryReposerty.findById(id);
		
	}

}
