package com.otp.twiliosms.dto;

import com.otp.twiliosms.enums.OTPStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Odinga David
 * @since : 10/8/21, Fri
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordResetResponse {

    private String message;
    private OTPStatus stastus;
}
