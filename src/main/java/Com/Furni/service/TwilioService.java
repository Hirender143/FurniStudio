package Com.Furni.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

@Service
public class TwilioService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String fromPhoneNumber;

    @PostConstruct
    public void init() {
        // Initialize Twilio with account SID and auth token
        Twilio.init(accountSid, authToken);
    }

    public void sendOtp(String phoneNumber, String otp) {
        Message message = Message.creator(
                new PhoneNumber(phoneNumber), // To number
                new PhoneNumber(fromPhoneNumber), // From number (Twilio)
                "Your OTP is: " + otp) // Message body
            .create();

        System.out.println("OTP sent with SID: " + message.getSid());
    }
}
