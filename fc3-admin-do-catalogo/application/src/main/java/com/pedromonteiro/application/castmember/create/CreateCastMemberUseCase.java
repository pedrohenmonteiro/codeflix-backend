package com.pedromonteiro.application.castmember.create;

import com.pedromonteiro.application.UseCase;

public sealed abstract class CreateCastMemberUseCase 
extends UseCase<CreateCastMemberCommand, CreateCastMemberOutput>
permits DefaultCreateCastMemberUseCase {
    
}
