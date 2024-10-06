package Com.Furni.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import Com.Furni.entity.User;
import Com.Furni.entity.contactus;
import Com.Furni.repositry.SignUpRepository;
import Com.Furni.repositry.furniRepository;

@Service
public class furniService {

	@Autowired
	private furniRepository furniRepository;

	@Autowired
	private SignUpRepository signUpRepository;

	// THIS IS FOR TO SAVE CONTACTUS
	public void saveContactus(contactus contactus) {
		furniRepository.save(contactus);
	}

	// THIS IS FOR SAVE SIGNUPFORM
	public void save(User user) {
		signUpRepository.save(user);
	}

	// THIS IS FOR LOGIN USING USERNAME & PASSWORD
	public User findByUsername(String username) {

		return signUpRepository.findByUsername(username);
	}

	// THIS IS FOR LOGIN THROUGH PHONE NUMBER-OTP
	public User findByphoneNumber(String phoneNumber) {
		return signUpRepository.findByphoneNumber(phoneNumber);
	}

	public boolean isAdmin(String username) {
		Boolean isAdmin = signUpRepository.findAdminStatusByUsername(username);
		return isAdmin != null && isAdmin;
		// return isAdmin;
	}

}
