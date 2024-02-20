package com.pedromonteiro.application.category.retrieve.list;

import com.pedromonteiro.application.UseCase;
import com.pedromonteiro.domain.pagination.CategorySearchQuery;
import com.pedromonteiro.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>>{
    
}
