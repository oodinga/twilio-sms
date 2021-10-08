package com.otp.twiliosms.dto;

import lombok.Data;

/**
 * @author : Odinga David
 * @since : 10/8/21, Fri
 */

@Data
public class PasswordResetRequest {

    private String phoneNumber; //sendTo
    private String username;
    private String oneTimePassword;
}
