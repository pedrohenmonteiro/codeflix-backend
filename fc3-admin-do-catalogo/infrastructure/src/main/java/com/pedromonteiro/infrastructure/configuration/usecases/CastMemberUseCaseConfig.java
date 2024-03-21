package com.pedromonteiro.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pedromonteiro.application.castmember.create.CreateCastMemberUseCase;
import com.pedromonteiro.application.castmember.create.DefaultCreateCastMemberUseCase;
import com.pedromonteiro.application.castmember.delete.DefaultDeleteCastMemberUseCase;
import com.pedromonteiro.application.castmember.delete.DeleteCastMemberUseCase;
import com.pedromonteiro.application.castmember.retrieve.get.DefaultGetCastMemberByIdUseCase;
import com.pedromonteiro.application.castmember.retrieve.get.GetCastMemberByIdUseCase;
import com.pedromonteiro.application.castmember.retrieve.list.DefaultListCastMembersUseCase;
import com.pedromonteiro.application.castmember.retrieve.list.ListCastMembersUseCase;
import com.pedromonteiro.application.castmember.update.DefaultUpdateCastMemberUseCase;
import com.pedromonteiro.application.castmember.update.UpdateCastMemberUseCase;
import com.pedromonteiro.domain.castmember.CastMemberGateway;

@Configuration
public class CastMemberUseCaseConfig {

    private final CastMemberGateway castMemberGateway;

    public CastMemberUseCaseConfig(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Bean
    public CreateCastMemberUseCase createCastMemberUseCase() {
        return new DefaultCreateCastMemberUseCase(castMemberGateway);
    }

    @Bean
    public DeleteCastMemberUseCase deleteCastMemberUseCase() {
        return new DefaultDeleteCastMemberUseCase(castMemberGateway);
    }

    @Bean
    public GetCastMemberByIdUseCase getCastMemberByIdUseCase() {
        return new DefaultGetCastMemberByIdUseCase(castMemberGateway);
    }

    @Bean
    public ListCastMembersUseCase listCastMembersUseCase() {
        return new DefaultListCastMembersUseCase(castMemberGateway);
    }

    @Bean
    public UpdateCastMemberUseCase updateCastMemberUseCase() {
        return new DefaultUpdateCastMemberUseCase(castMemberGateway);
    }
}

