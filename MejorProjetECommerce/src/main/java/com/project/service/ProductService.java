package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Product;
import com.project.reposerty.ProductReposerty;
@Service
public class ProductService {
	@Autowired
    ProductReposerty productReposerty;
	public List<Product> getAllProducts(){
	return	productReposerty.findAll();
	}
	public void addproduct(Product product) {
		productReposerty.save(product);
	}
	public void removeProductById(long id) {
		productReposerty.deleteById(id);
	}
	public Optional<Product> getProductById(long id){
		return productReposerty.findById(id);
	}
	public List<Product> getAllProductByCategory(int id){
		return productReposerty.findAllByCategory_Id(id);
	}
	
	
}
