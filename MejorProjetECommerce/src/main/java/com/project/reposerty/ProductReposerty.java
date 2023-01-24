package com.project.reposerty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Product;

public interface ProductReposerty extends JpaRepository<Product, Long> {

	List<Product> findAllByCategory_Id(int id);

}
