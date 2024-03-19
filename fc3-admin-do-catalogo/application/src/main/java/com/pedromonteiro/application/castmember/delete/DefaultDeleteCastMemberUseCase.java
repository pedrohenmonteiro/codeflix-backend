package com.pedromonteiro.application.castmember.delete;

import java.util.Objects;

import com.pedromonteiro.domain.castmember.CastMemberGateway;
import com.pedromonteiro.domain.castmember.CastMemberID;

public final class DefaultDeleteCastMemberUseCase extends DeleteCastMemberUseCase {

    private final CastMemberGateway castMemberGateway;
    
    public DefaultDeleteCastMemberUseCase(CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public void execute(String anId) {
        this.castMemberGateway.deleteById(CastMemberID.from(anId));
    }
    
}
