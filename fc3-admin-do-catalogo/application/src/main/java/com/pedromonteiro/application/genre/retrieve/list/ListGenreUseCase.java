package com.pedromonteiro.application.genre.retrieve.list;

import com.pedromonteiro.application.UseCase;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.pagination.SearchQuery;

public abstract class ListGenreUseCase extends UseCase<SearchQuery, Pagination<GenreListOutput>> {
    
}
