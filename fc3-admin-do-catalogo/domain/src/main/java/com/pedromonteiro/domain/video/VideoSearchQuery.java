package com.pedromonteiro.domain.video;

import java.util.Set;

import com.pedromonteiro.domain.castmember.CastMemberID;
import com.pedromonteiro.domain.category.CategoryID;
import com.pedromonteiro.domain.genre.GenreID;

public record VideoSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction,
        Set<CastMemberID> castMembers,
        Set<CategoryID> categories,
        Set<GenreID> genres
) {
}
