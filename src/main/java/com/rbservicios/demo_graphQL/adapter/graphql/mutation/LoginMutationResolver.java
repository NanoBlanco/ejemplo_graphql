package com.rbservicios.demo_graphQL.adapter.graphql.mutation;

import com.rbservicios.demo_graphQL.application.command.usecase.LoginUseCase;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginMutationResolver {

    private final LoginUseCase loginUseCase;

    public LoginMutationResolver(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @MutationMapping
    public String login(@Argument Long userId) {
        return loginUseCase.login(userId);
    }
}
