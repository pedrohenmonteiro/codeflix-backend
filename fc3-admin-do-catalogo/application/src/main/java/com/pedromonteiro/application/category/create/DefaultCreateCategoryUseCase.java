package com.pedromonteiro.application.category.create;

import java.util.Objects;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryGateway;
import com.pedromonteiro.domain.validation.handler.Notification;
import com.pedromonteiro.domain.validation.handler.ThrowsValidationHandler;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {

        final var notification = Notification.create();

        final var aCategory = Category.newCategory(
            aCommand.name(),
            aCommand.description(),
            aCommand.isActive()
        );

        aCategory.validate(notification);

        
        return CreateCategoryOutput.from(this.categoryGateway.create(aCategory));
    }
    
}
