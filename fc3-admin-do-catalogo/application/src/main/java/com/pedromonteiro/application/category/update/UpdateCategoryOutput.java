package com.pedromonteiro.application.category.update;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryID;

public record UpdateCategoryOutput(
    CategoryID id
) {
    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
