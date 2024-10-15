package Com.Furni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Com.Furni.entity.Cart;
import Com.Furni.service.CartService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

//	@GetMapping("/cart")
//	public String cartView(@RequestParam("userId") Long userId, Model model) {
//
//
//		
//		Cart userCart = cartService.findCartByUserId(userId);
//		
//
//		model.addAttribute("cart", userCart);
//		
//
//		return "cart";
//		
//	}
	
	
	@GetMapping("/cart")
	public String cartView(HttpSession session, Model model) {
	    
		
	    Long userId = (Long) session.getAttribute("id");
	    System.out.println(userId);

	    if(userId!=null) {
	    Cart userCart = cartService.findCartByUserId(userId);
	    
	    
//	    double total = userCart.getProducts().stream()
//                .mapToDouble(product -> product.getItemPrice())
//                .sum();
//            System.out.println("Cart Total: $" + total);
	    System.out.println("this is the User  at line 51"+userCart);
	    
//	    for(int i =0;i<userCart.getProducts().size();i++) {
//	    	System.out.println(userCart.getProducts().get(i).getItemName()+" this is the products");
//	    	
//	    }
	    
	    
	    model.addAttribute("carts", userCart);
	    System.out.println("all details come in usercart");
	    return "cart";
	    }
	   

	   
	   return null;
	}

	@PostMapping("/cart/add/{productId}")
	public String addToCart(@PathVariable Long productId) {

		cartService.addProductToCart(productId);
		return "redirect:/cart";
	}

}
