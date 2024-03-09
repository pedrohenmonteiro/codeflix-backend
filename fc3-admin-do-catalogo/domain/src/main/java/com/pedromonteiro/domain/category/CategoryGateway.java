package com.pedromonteiro.domain.category;

import java.util.Optional;

import com.pedromonteiro.domain.pagination.SearchQuery;
import com.pedromonteiro.domain.pagination.Pagination;

public interface CategoryGateway {

    Category create(Category aCategory);

    void deleteById(CategoryID anId);

    Optional<Category> findById(CategoryID anId);

    Category update(Category aCategory);

    Pagination<Category> findAll(SearchQuery aQuery);
    
}
