package com.otp.twiliosms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Odinga David
 * @since : 10/8/21, Fri
 */

@Data
@Configuration
@ConfigurationProperties(prefix="twilio")
public class TwilioConfig {

    private String accountSid;
    private String authToken;
    private String trialNumber;
}
