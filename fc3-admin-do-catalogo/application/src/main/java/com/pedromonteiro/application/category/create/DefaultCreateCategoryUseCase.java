package com.pedromonteiro.application.category.create;

import java.util.Objects;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryGateway;
import com.pedromonteiro.domain.validation.handler.Notification;
import com.pedromonteiro.domain.validation.handler.ThrowsValidationHandler;

import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Either.Right;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {

        final var notification = Notification.create();

        final var aCategory = Category.newCategory(
            aCommand.name(),
            aCommand.description(),
            aCommand.isActive()
        );

        aCategory.validate(notification);

        if (notification.hasError()) {

        }
        return notification.hasError() ? API.Left(notification) : create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category aCategory) {
        return API.Try(() -> this.categoryGateway.create(aCategory))
            .toEither()
            .bimap(Notification::create, CreateCategoryOutput::from);
        
    }

    // private Either<Notification, CreateCategoryOutput> create(final Category aCategory) {
        
    //     try {
    //         return Right(CreateCategoryOutput.from(this.categoryGateway.create(aCategory)));
    //     } catch (Throwable t) {
    //         return Left(Notification.create(t));
    //     }
        
    // }
    
}
