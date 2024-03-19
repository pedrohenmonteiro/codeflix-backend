package com.pedromonteiro.application.castmember.retrieve.get;

import com.pedromonteiro.application.UseCase;

public sealed abstract class GetCastMemberByIdUseCase
    extends UseCase<String, CastMemberOutput>
    permits DefaultGetCastMemberByIdUseCase {
}