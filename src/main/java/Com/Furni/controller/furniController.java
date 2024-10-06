package Com.Furni.controller;

import java.io.IOException;


import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Com.Furni.entity.Product;
import Com.Furni.entity.User;
import Com.Furni.entity.contactus;
import Com.Furni.service.ProductService;
import Com.Furni.service.TwilioService;
import Com.Furni.service.emailService;
import Com.Furni.service.furniService;
import jakarta.servlet.http.HttpSession;

@Controller
public class furniController {

	@Autowired
	private furniService furniService;

	@Autowired
	private emailService emailService;

	@Autowired
	private TwilioService twilioService;

	@Autowired
	private ProductService productService;

	// THIS IS TO MAPPING ENTIRE MESSAGES
	@GetMapping("/index")
	public String homeView() {
		return "index";

	}

	@GetMapping("/services")
	public String servicesView() {
		return "services";

	}

	@GetMapping("/shop")
	public String shopView(Model model) {
		List<Product> product = productService.getAllProducts();
		model.addAttribute("products", product);
		return "shop";

	}

	@GetMapping("/image/{id}")
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long id) {
		
		Product product = productService.getProductById(id); // Fetch product from service

		if (product != null && product.getItemImage() != null) {
			
			
			MediaType mediaType = (product.getItemImageType() != null) ? MediaType.parseMediaType(product.getItemImageType()) : detectImageType(product.getItemImage());
			
			ByteArrayResource byteArrayResource = new ByteArrayResource(product.getItemImage());
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"product-" + id + "\"")
					.contentType(mediaType).contentLength(product.getItemImage().length).body(byteArrayResource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Helper method to detect image type
	private MediaType detectImageType(byte[] image) {
		// Simplified detection, you may want to refine this based on your use case
		String header = new String(image, 0, 4);
		if (header.startsWith("\u00FF\u00D8\u00FF")) {
			return MediaType.IMAGE_JPEG;
		} else if (header.startsWith("\u0089PNG")) {
			return MediaType.IMAGE_PNG;
		}
		return MediaType.APPLICATION_OCTET_STREAM; // Default type if unknown
	}

	@GetMapping("/about")
	public String aboutView() {
		return "about";

	}

	

	@GetMapping("/blog")
	public String blogView() {
		return "blog";

	}

	@GetMapping("/addProduct")
	public String addProductView(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";

	}

	// THIS IS FOR CONTACTUS FORM
	@GetMapping("/contact")
	public String contactView(Model model) {

		model.addAttribute("contact", new contactus());
		return "contact";

	}

	@PostMapping("/save")
	public String savecontact(@ModelAttribute("contact") contactus contactus, RedirectAttributes redirect) {

		furniService.saveContactus(contactus);

		return "redirect:index";
	}

	// NOW THIS IS FOR SIGNUP USER
	@GetMapping("/SignupForm")
	public String showSignupForm(Model model) {

		model.addAttribute("user", new User());
		return "SignupForm";
	}

	@PostMapping("/savesignup")
	public String save(@ModelAttribute("user") User user, RedirectAttributes redirect) {
		furniService.save(user);
		emailService.sendSignupEmail(user.getEmail(), user.getFullName());
		return "redirect:index";
	}

	// THIS IS FOR LOGIN PAGE
	@GetMapping("/Login")
	public String loginView() {
		
		return "Login";
	}

	@PostMapping("/Login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, RedirectAttributes rs) {
		User user = furniService.findByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("name", user.getUsername());
			session.setAttribute("user", user);
			boolean isAdmin = furniService.isAdmin(username);
			System.out.println(isAdmin);
			if (isAdmin) {
				session.setAttribute("isAdmin", true);
			} else {
				session.setAttribute("isAdmin", false);
			}
			System.out.println(user.getUsername());
			return "index";
		}

		else {
			rs.addFlashAttribute("warning", "username/password is not correct");
			return "Login";
		}

	}

	@GetMapping("/Logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";

	}

	@GetMapping("/otpLogin")
	public String otplogin() {

		return "OtpLogin";
	}

	@PostMapping("/otpSend")
	public String otpSend(@RequestParam("phoneNumber") String phoneNumber, HttpSession session,
			RedirectAttributes redirect) {
		User user = furniService.findByphoneNumber(phoneNumber);
		System.out.println(phoneNumber);
		System.out.println(user.getUsername());

		furniService.findByphoneNumber(phoneNumber);

		if (phoneNumber != null && !phoneNumber.isEmpty()) {
			Random random = new Random();
			int otpstr = 1000 + random.nextInt(9000);
			String otp = String.valueOf(otpstr);
			System.out.println("OTP GENERATED : " + otp);

			session.setAttribute("otp", otp);
			session.setAttribute("name", user.getUsername());

			System.out.println("before isAdmin invoked");
			boolean isAdmin = furniService.isAdmin(phoneNumber);

			System.out.println("isAdmin invoked");
			if (isAdmin) {
				session.setAttribute("isAdmin", true);

			} else {
				session.setAttribute("isAdmin", false);
			}

			twilioService.sendOtp(phoneNumber, otp);
			System.out.println("call sendOtp method invoked");

		}
		return "verifyOtp";

	}

	@PostMapping("/verifyOtp")
	public String verifyOtp(@RequestParam("otp") String otp, HttpSession session, RedirectAttributes redirect) {

		String sessionOtp = (String) session.getAttribute("otp");

		if (sessionOtp != null && sessionOtp.equals(otp)) {

			System.out.println("here");
			return "redirect:/index";

			// return "redirect:/index?name=" + name;
		} else {
			// OTP is invalid, show an error message
			System.out.println("error occur");
			redirect.addFlashAttribute("error", "Invalid OTP");
			return "redirect:contact";
		}
	}
	

	@PostMapping("saveProduct")
	public String addProduct(@ModelAttribute Product product,
			RedirectAttributes redirect) {
		try {
			if (!product.getImageFile().isEmpty()) {
				product.setItemImage(product.getImageFile().getBytes());
				product.setItemImageType(product.getImageFile().getContentType());
				
				System.out.println(product.getImageFile().getContentType());
				
			}
			productService.saveProduct(product);
			redirect.addFlashAttribute("Success", "Product Added Successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/shop";

	}

}
