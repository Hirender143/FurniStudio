package Com.Furni.service;



import org.springframework.stereotype.Service;

import Com.Furni.entity.User;
import Com.Furni.repositry.UserRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	
	
	private final UserRepository userRepository;
	private final HttpSession httpSession;

	

	public UserService(UserRepository userRepository, HttpSession httpSession) {
		super();
		this.userRepository = userRepository;
		this.httpSession = httpSession;
	}



	public User findCurrentUser() {
		User user = (User) httpSession.getAttribute("user");
		return user;
	}
	
	
	
	
	
	
	

}
