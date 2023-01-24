package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.service.CategoriesService;
import com.project.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoriesService categoriesService;
	@Autowired
	ProductService productService;
	@GetMapping("/")
	public String home(Model model) {
		return "index";
		
	}
	@GetMapping("/shop")
	public String shop(Model model) {
		
		model.addAttribute("categories",categoriesService.getAllCategories());
		model.addAttribute("products",productService.getAllProducts());
		return "shop";
	}
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model,@PathVariable int id) {
		
		model.addAttribute("categories",categoriesService.getAllCategories());
		model.addAttribute("products",productService.getAllProductByCategory(id));
		return "shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable int id) {
		
		model.addAttribute("product",productService.getProductById(id).get());
		return "viewProduct";
	}
	
	

}
