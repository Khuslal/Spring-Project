package com.bway.springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.Cart;
import com.bway.springproject.model.Product;
import com.bway.springproject.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Cart> findByUser(User user);
	Cart findByUserAndProduct(User user, Product product);
}
