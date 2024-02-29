package com.pedromonteiro.infrastructure.api.controllers;

import java.net.URI;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pedromonteiro.application.category.create.CreateCategoryCommand;
import com.pedromonteiro.application.category.create.CreateCategoryOutput;
import com.pedromonteiro.application.category.create.CreateCategoryUseCase;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.validation.handler.Notification;
import com.pedromonteiro.infrastructure.api.CategoryAPI;
import com.pedromonteiro.infrastructure.category.models.CreateCategoryRequest;

@RestController
public class CategoryController implements CategoryAPI {

    private CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
            input.name(),
            input.description(),
            input.active() == null ? true : input.active()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output -> ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
            .fold(onError, onSuccess);
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listCategories'");
    }
    
}
