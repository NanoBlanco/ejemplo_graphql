package com.rbservicios.demo_graphQL.application.command.usecase;

import com.rbservicios.demo_graphQL.application.command.port.JwtTokenProvider;
import com.rbservicios.demo_graphQL.domain.model.User;
import com.rbservicios.demo_graphQL.domain.repository.UserRepository;

public class LoginUseCase {

    private final UserRepository repository;
    private final JwtTokenProvider tokenProvider;

    public LoginUseCase(
            UserRepository repository,
            JwtTokenProvider tokenProvider) {
        this.repository = repository;
        this.tokenProvider = tokenProvider;
    }

    public String login(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        return tokenProvider.generateToken(user);
    }

}
