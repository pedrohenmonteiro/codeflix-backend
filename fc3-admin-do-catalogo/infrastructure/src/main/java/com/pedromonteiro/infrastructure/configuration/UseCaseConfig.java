package com.pedromonteiro.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pedromonteiro.application.category.create.CreateCategoryUseCase;
import com.pedromonteiro.application.category.create.DefaultCreateCategoryUseCase;
import com.pedromonteiro.application.category.delete.DefaultDeleteCategoryUseCase;
import com.pedromonteiro.application.category.delete.DeleteCategoryUseCase;
import com.pedromonteiro.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.pedromonteiro.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.pedromonteiro.application.category.retrieve.list.DefaultListCategoriesUseCase;
import com.pedromonteiro.application.category.retrieve.list.ListCategoriesUseCase;
import com.pedromonteiro.application.category.update.DefaultUpdateCategoryUseCase;
import com.pedromonteiro.application.category.update.UpdateCategoryUseCase;
import com.pedromonteiro.domain.category.CategoryGateway;

@Configuration
public class UseCaseConfig {
    
    private final CategoryGateway categoryGateway;

    public UseCaseConfig(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

     @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }
    

}
