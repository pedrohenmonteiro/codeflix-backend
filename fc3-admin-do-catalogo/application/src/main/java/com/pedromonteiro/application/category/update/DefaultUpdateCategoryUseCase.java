package com.pedromonteiro.application.category.update;

import java.util.Objects;
import java.util.function.Supplier;

import com.pedromonteiro.application.category.create.CreateCategoryOutput;
import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryGateway;
import com.pedromonteiro.domain.category.CategoryID;
import com.pedromonteiro.domain.exceptions.DomainException;
import com.pedromonteiro.domain.validation.Error;
import com.pedromonteiro.domain.validation.handler.Notification;

import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Either.Left;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;



    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }



    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {

        var anId = CategoryID.from(aCommand.id());
        
        final var aCategory = categoryGateway.findById(anId)
            .orElseThrow(notFound(anId));

        final var notification = Notification.create();

         aCategory
            .update(aCommand.name(),aCommand.description(), aCommand.isActive())
            .validate(notification);

        return notification.hasError() ? API.Left(notification) : update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
        return API.Try(() -> this.categoryGateway.update(aCategory))
            .toEither()
            .bimap(Notification::create, UpdateCategoryOutput::from);
        
    }

    private Supplier<DomainException> notFound(CategoryID anId) {
        return () -> DomainException.with(new Error("Category with ID %s was not found".formatted(anId.getValue())));
    }
    
}
