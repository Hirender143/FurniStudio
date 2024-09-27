package Com.Furni.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailService {

	@Value("${spring.mail.username}")
	private String fromEmail;

	private final JavaMailSender emailSender;

	public emailService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void sendSignupEmail(String email, String fullName) {
		System.out.println("method call: "+email);
		System.out.println("method call: "+fullName);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(email);
		message.setSubject("Welcome to Furni Company");
		message.setText("Dear " + fullName + ",\n\nThank you for signing up at Furni Company. Welcome to the world of furniture!\n\nBest Regards,\nFurni Company");
		emailSender.send(message);
	}

}
