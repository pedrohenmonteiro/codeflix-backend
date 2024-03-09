package com.pedromonteiro.domain.pagination;

public record CategorySearchQuery(
    int page,
    int perPage,
    String terms,
    String sort,
    String direction
) {
   
}
