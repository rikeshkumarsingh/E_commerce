package com.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dto.ProductDto;
import com.project.model.Category;
import com.project.model.Product;
import com.project.service.CategoriesService;
import com.project.service.ProductService;

import jakarta.persistence.criteria.Path;

@Controller
public class AdminControl {
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	@Autowired
	CategoriesService categoriesService;
	@Autowired
	ProductService productService;
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories",categoriesService.getAllCategories());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String getcatadd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	@PostMapping("/admin/categories/add")
	public String postcatadd(@ModelAttribute("category")Category category) {
		categoriesService.addCategories(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String delCat(@PathVariable int id) {
		categoriesService.removeCategoryById(id);
		return "redirect:/admin/categories";

	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id,Model model) {
		Optional<Category> category =categoriesService.update(id);
		if(category.isPresent()) {
			model.addAttribute("category",category.get());
			return "categoriesAdd";
		}
		else 
			return"404";
		
	}
	//product section
	@GetMapping("/admin/products")
	public String getproduct(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	@GetMapping("/admin/products/add")
	public String addproduct(Model model) {
		model.addAttribute("productDTO", new ProductDto());
		model.addAttribute("categories" , categoriesService.getAllCategories());
		return "productsAdd";
	}
	@PostMapping("/admin/products/add")
	public String addproduct(@ModelAttribute("productDTO)")ProductDto productDTO,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam("imgName")String imgName)throws IOException{
		Product product =new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoriesService.update(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID=file.getOriginalFilename();
			java.nio.file.Path fileNameAndPath= Paths.get(uploadDir, imageUUID);		
			Files.write(fileNameAndPath,file.getBytes());
			
		}
		else {
			imageUUID=imgName;
		}
		product.setImageName(imageUUID);
		productService.addproduct(product);
		
		
		
		
		return "redirect:/admin/products";
		
	}
	@GetMapping("/admin/product/delete/{id}")
	public String delproduct(@PathVariable long id) {
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/update/{id}")
	public String updateproduct(@PathVariable long id,Model model) {
		Product product =productService.getProductById(id).get();
		ProductDto productDto=new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setPrice(product.getPrice());
		productDto.setWeight(product.getWeight());
		productDto.setDescription(product.getDescription());
		productDto.setImageName(product.getImageName());
		model.addAttribute("categories",categoriesService.getAllCategories());
		model.addAttribute("productDTO",productDto);
		
		
		
		
		return "productsAdd";
	}
	
	

}
