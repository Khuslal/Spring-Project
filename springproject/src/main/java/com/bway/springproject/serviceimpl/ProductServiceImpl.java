package com.bway.springproject.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bway.springproject.model.Product;
import com.bway.springproject.repository.ProductRepository;
import com.bway.springproject.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepo;

	ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@Override
	public List<Product> saveAllProd(List<Product> products) {
		return productRepo.saveAll(products);
	}

}
