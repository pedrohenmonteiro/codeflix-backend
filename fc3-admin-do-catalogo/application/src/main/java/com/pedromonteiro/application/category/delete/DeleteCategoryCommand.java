package com.pedromonteiro.application.category.delete;

public record DeleteCategoryCommand(
    String id
) {
    public static DeleteCategoryCommand with(String anId) {
        return new DeleteCategoryCommand(anId);
    }
}
