package com.pedromonteiro.application.category.update;

import com.pedromonteiro.application.UseCase;
import com.pedromonteiro.domain.validation.handler.Notification;

import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> { 
    
}
