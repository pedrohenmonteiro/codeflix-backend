package com.pedromonteiro.application.category.create;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryID;

public record CreateCategoryOutput(
    CategoryID id
) {
    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }
}
