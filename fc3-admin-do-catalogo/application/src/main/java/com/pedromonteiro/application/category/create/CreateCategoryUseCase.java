package com.pedromonteiro.application.category.create;

import com.pedromonteiro.application.UseCase;
import com.pedromonteiro.domain.validation.handler.Notification;

import io.vavr.control.Either;



public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
    
}
