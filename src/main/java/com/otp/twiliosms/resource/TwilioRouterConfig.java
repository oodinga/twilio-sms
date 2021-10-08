package com.otp.twiliosms.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author : Odinga David
 * @since : 10/8/21, Fri
 */

@Configuration
@RequiredArgsConstructor
public class TwilioRouterConfig {

    private final TwilioOTPHandler handler;

    @Bean
    public RouterFunction<ServerResponse> handleOTPAuth(){
        return RouterFunctions.route()
                .POST("/router/sendOTP", handler::sendOTP)
                .POST("/router/validateOTP", handler::validateOTP)
                .build();
    }
}
