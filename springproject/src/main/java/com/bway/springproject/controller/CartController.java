package com.bway.springproject.controller;

import com.bway.springproject.repository.CartRepository;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Cart;
import com.bway.springproject.model.Product;
import com.bway.springproject.model.User;
import com.bway.springproject.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	private final CartRepository cartRepository;
	private final ProductRepository productRepo;

	// Inject both repositories via constructor injection
	public CartController(CartRepository cartRepository, ProductRepository productRepo) {
		this.cartRepository = cartRepository;
		this.productRepo = productRepo;
	}

	@GetMapping("/addToCart")
	public String getCart(@RequestParam("id") int pid, HttpSession session) {
		User activeUser = (User) session.getAttribute("activeUser");

		if (activeUser == null) {
			return "redirect:/login";
		}

		// Fetch Product from Repository
		Product product = productRepo.findById(pid).orElse(null);

		if (product != null) {
			Cart newCartItem = new Cart();
			newCartItem.setUser(activeUser);
			newCartItem.setProduct(product);
			newCartItem.setQuantity(1);
			cartRepository.save(newCartItem);
		}

		// Added redirect so the user goes back to the shop page after adding an item
		return "redirect:/";
	}
	
	// Cart View Page
	@GetMapping("/cart")
	public String viewCart(HttpSession session, Model model) {
		User activeUser = (User) session.getAttribute("activeUser");
		if (activeUser == null) {
			return "redirect:/login";
		}

		// Fetch all cart items belonging specifically to the logged-in user
		List<Cart> cartItems = cartRepository.findByUser(activeUser);
		
		// Calculate total price to show on the checkout page
		double grandTotal = cartItems.stream()
				.mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
				.sum();

		model.addAttribute("cartItems", cartItems);
		model.addAttribute("grandTotal", grandTotal);
		
		// Add cart badge count for header display on this page too
		model.addAttribute("cartCount", cartItems.stream().mapToInt(Cart::getQuantity).sum());

		return "CartPage"; // Name of your Thymeleaf HTML file (CartPage.html)
	}
}
