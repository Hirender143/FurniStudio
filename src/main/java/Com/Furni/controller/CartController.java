package Com.Furni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Com.Furni.entity.Cart;
import Com.Furni.entity.Product;
import Com.Furni.entity.User;
import Com.Furni.service.CartService;
import Com.Furni.service.ProductService;
import Com.Furni.service.UserService;
import Com.Furni.service.furniService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	@GetMapping("/cart")
	public String cartView() {

		return "cart";

	}

	@PostMapping("/cart/add/{productId}")
	public String addToCart(@PathVariable Long productId) {

		cartService.addProductToCart(productId);
		return "redirect:/cart";
	}

}
