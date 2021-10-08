package com.otp.twiliosms.resource;

import com.otp.twiliosms.dto.PasswordResetRequest;
import com.otp.twiliosms.service.TwilioOTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author : Odinga David
 * @since : 10/8/21, Fri
 */

@Component
@RequiredArgsConstructor
public class TwilioOTPHandler {

    private final TwilioOTPService otpService;

    public Mono<ServerResponse> sendOTP(ServerRequest request){
        return request.bodyToMono(PasswordResetRequest.class)
                .flatMap(otpService::sendOTP)
                .flatMap(response -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromValue(response)));
    }

    public Mono<ServerResponse> validateOTP(ServerRequest request){
        return request.bodyToMono(PasswordResetRequest.class)
                .flatMap(dto-> otpService.validateOTP(dto.getOneTimePassword(), dto.getUsername()))
                .flatMap(response -> ServerResponse.status(HttpStatus.OK)
                        .bodyValue(response));
    }
}
