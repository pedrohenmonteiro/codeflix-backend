package com.pedromonteiro.application.castmember.create;

import com.pedromonteiro.domain.castmember.CastMemberType;

public record CreateCastMemberCommand(
    String name,
    CastMemberType type
) {
    
    public static CreateCastMemberCommand with(
        String aName,
        CastMemberType aType
    ) {
        return new CreateCastMemberCommand(aName, aType);
    }
}
