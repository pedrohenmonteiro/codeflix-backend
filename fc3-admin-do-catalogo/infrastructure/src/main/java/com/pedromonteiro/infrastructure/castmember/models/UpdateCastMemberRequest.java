package com.pedromonteiro.infrastructure.castmember.models;

import com.pedromonteiro.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}
