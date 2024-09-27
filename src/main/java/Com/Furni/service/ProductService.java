package Com.Furni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.Furni.entity.Product;
import Com.Furni.repositry.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// Create a new product
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	// Get a product by ID
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	// Get all products
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	// Update an existing product
	public Product updateProduct(Long id, Product productDetails) {
		return productRepository.findById(id)
				.map(product -> {
			product.setItemName(productDetails.getItemName());
			product.setItemPrice(productDetails.getItemPrice());
			product.setItemDescription(productDetails.getItemDescription());
			product.setItemImage(productDetails.getItemImage());
			return productRepository.save(product);
		}).orElseThrow(() -> new IllegalArgumentException("Product not found with id " + id));
	}

	// Delete a product by ID
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
