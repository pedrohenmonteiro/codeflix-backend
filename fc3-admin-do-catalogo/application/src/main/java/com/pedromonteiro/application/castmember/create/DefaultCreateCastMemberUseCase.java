package com.pedromonteiro.application.castmember.create;

import java.util.Objects;

import com.pedromonteiro.domain.castmember.CastMember;
import com.pedromonteiro.domain.castmember.CastMemberGateway;
import com.pedromonteiro.domain.exceptions.NotificationException;
import com.pedromonteiro.domain.validation.handler.Notification;

public final class DefaultCreateCastMemberUseCase extends CreateCastMemberUseCase{

    private final CastMemberGateway castMemberGateway;


    
    public DefaultCreateCastMemberUseCase(CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }



    @Override
    public CreateCastMemberOutput execute(final CreateCastMemberCommand aCommand) {
        final var aName = aCommand.name();
        final var aType = aCommand.type();

        final var notification = Notification.create();
        final var aMember = notification.validate(() -> CastMember.newMember(aName, aType));

        if (notification.hasError()) {
            notify(notification);
        }

        return CreateCastMemberOutput.from(this.castMemberGateway.create(aMember));
    }



    private void notify(final Notification notification) {
        throw new NotificationException("Could not create Aggregate CastMember", notification);
    }
    
}
