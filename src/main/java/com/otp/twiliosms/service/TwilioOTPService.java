package com.otp.twiliosms.service;

import com.otp.twiliosms.config.TwilioConfig;
import com.otp.twiliosms.dto.PasswordResetRequest;
import com.otp.twiliosms.dto.PasswordResetResponse;
import com.otp.twiliosms.enums.OTPStatus;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author : Odinga David
 * @since : 10/8/21, Fri
 */

@Service
@RequiredArgsConstructor
public class TwilioOTPService {

    private final TwilioConfig twilioConfig;

    Map<String, String> otpMap= new HashMap<>();


    public Mono<PasswordResetResponse> sendOTP(PasswordResetRequest passwordReset){

        PasswordResetResponse response =null;

        try {
            PhoneNumber to = new PhoneNumber(passwordReset.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

            String otp = generateOTP();
            String OTPmessage = "Dear customer, Your OTP is:  " + otp + ". Use it to complete registration. OTP stays valid for 1 hour";

            Message message = Message
                    .creator(to, from, OTPmessage)
                    .create();

            otpMap.put(passwordReset.getUsername(), otp);
            response = new PasswordResetResponse(OTPmessage, OTPStatus.DELIVERED);
        }catch (Exception e){
            response = new PasswordResetResponse("Error while sending OTP", OTPStatus.FAILED);
        }

        return Mono.just(response);
    }

    public Mono<String> validateOTP(String OTP, String username){
        if(OTP.equals(otpMap.get(username))){
            otpMap.remove(username);
            return Mono.just("Validated successfully");
        }

        return Mono.just("Invalid OTP. Retry");
    }

    private String generateOTP(){
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }


}
