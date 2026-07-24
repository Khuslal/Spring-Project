package com.bway.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bway.springproject.repository.ProductRepository;


@Controller
public class ProductGalleryController {
	
	private final ProductRepository productRepo;

	ProductGalleryController(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@GetMapping("/productGallery")
	public String getProducts(Model model) {
		model.addAttribute("productList", productRepo.findAll());
		return "ProductGalleryForm";
	}
}
