package com.pedromonteiro.application.category.retrieve.list;

import java.util.Objects;

import com.pedromonteiro.domain.category.CategoryGateway;
import com.pedromonteiro.domain.pagination.SearchQuery;
import com.pedromonteiro.domain.pagination.Pagination;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase {

    private final CategoryGateway categoryGateway;

    

    public DefaultListCategoriesUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }



    @Override
    public Pagination<CategoryListOutput> execute(final SearchQuery aQuery) {
        return this.categoryGateway.findAll(aQuery)
            .map(CategoryListOutput::from);
    }
    
}
