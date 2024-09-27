package Com.Furni.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // This stores the user associated with the cart

    @ManyToMany
    @JoinTable(
        name = "cart_products", // Join table to link carts and products
        joinColumns = @JoinColumn(name = "cart_id"), // Foreign key from cart
        inverseJoinColumns = @JoinColumn(name = "product_id") // Foreign key from product
    )
    private Set<Product> products = new HashSet<>();

    public Cart() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    // Add method for adding products
    public void addProduct(Product product) {
        this.products.add(product);
    }

    // Remove method for removing products
    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    // Method to display the items with name and price in the cart
    public void displayCartItems() {
        for (Product product : products) {
            System.out.println("Item: " + product.getItemName() + " | Price: " + product.getItemPrice());
        }
    }
}
