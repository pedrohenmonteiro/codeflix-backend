package com.pedromonteiro.application.category.retrieve.get;

import java.util.Objects;
import java.util.function.Supplier;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryGateway;
import com.pedromonteiro.domain.category.CategoryID;
import com.pedromonteiro.domain.exceptions.NotFoundException;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;


    public DefaultGetCategoryByIdUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }



    @Override
    public CategoryOutput execute(final String anIn) {
       final var anCategoryID = CategoryID.from(anIn);

       return this.categoryGateway.findById(anCategoryID)
        .map(CategoryOutput::from)
        .orElseThrow(notFound(anCategoryID));
    }
    
  
    private Supplier<NotFoundException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }
}
