package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bway.springproject.repository.ProductRepository;


@Controller
public class ProductGalleryController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/productGallery")
	public String getProducts(Model model) {
		model.addAttribute("productList", productRepo.findAll());
		return "ProductGalleryForm";
	}
}
