package com.pedromonteiro.application.castmember.create;

import com.pedromonteiro.domain.castmember.CastMember;
import com.pedromonteiro.domain.castmember.CastMemberID;

public record CreateCastMemberOutput(
    String id
) {
    public static CreateCastMemberOutput from(final CastMember aMember) {
        return new CreateCastMemberOutput(aMember.getId().getValue());
    };

    public static CreateCastMemberOutput from(CastMemberID anId) {
        return new CreateCastMemberOutput(anId.getValue());
    };
}
