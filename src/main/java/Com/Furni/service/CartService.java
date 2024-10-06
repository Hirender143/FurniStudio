package Com.Furni.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import Com.Furni.entity.Cart;
import Com.Furni.entity.Product;
import Com.Furni.entity.User;
import Com.Furni.repositry.cartRepository;
import Com.Furni.repositry.ProductRepository;

import java.util.Optional;

@Service
public class CartService {

	@Autowired
	private cartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	// Method to create a new cart
	public Cart createCart() {
		Cart newCart = new Cart();
		System.out.println("before save");

		return cartRepository.save(newCart); // Save and return the newly created cart

	}

	public void addProductToCart(Long productId) {
		User currentUser = userService.findCurrentUser();
		Cart cart = findCartByUserId(currentUser.getId());

		if (cart == null) {
			cart = new Cart();
		}
		cart.setUser(currentUser);
		Product product = productService.getProductById(productId);

		cart.getProducts().add(product);
		cartRepository.save(cart);
	}

	public void removeProductFromCart(Long cartId, Long productId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

		// Assuming your Cart entity has a removeProduct method
		Optional<Product> productOpt = productRepository.findById(productId);
		if (productOpt.isPresent()) {
			cart.removeProduct(productOpt.get()); // Remove product from cart
			cartRepository.save(cart); // Save the updated cart
		} else {
			throw new RuntimeException("Product not found in cart");
		}
	}

	public Cart findCartByUserId(Long id) {
		return cartRepository.findByUserId(id);
	}

}
