package com.pedromonteiro.application.castmember.delete;

import com.pedromonteiro.application.UnitUseCase;

public sealed abstract class DeleteCastMemberUseCase 
extends UnitUseCase<String>
permits DefaultDeleteCastMemberUseCase
{
    
}
