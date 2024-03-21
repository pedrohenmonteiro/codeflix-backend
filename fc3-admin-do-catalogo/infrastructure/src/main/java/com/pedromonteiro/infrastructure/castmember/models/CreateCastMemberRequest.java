package com.pedromonteiro.infrastructure.castmember.models;

import com.pedromonteiro.domain.castmember.CastMemberType;

public record CreateCastMemberRequest(String name, CastMemberType type) {
}
