package com.pedromonteiro.application.castmember.create;

import com.pedromonteiro.domain.castmember.CastMember;

public record CreateCastMemberOutput(
    String id
) {
    public static CreateCastMemberOutput from(final CastMember aMember) {
        return new CreateCastMemberOutput(aMember.getId().getValue());
    };
}
