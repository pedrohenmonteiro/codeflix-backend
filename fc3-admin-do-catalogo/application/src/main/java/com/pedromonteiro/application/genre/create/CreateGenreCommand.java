package com.pedromonteiro.application.genre.create;

public record CreateGenreCommand(
    String name,
    boolean isActive
) {
    
}



/*
 *    private String name;
    private boolean active;
    private List<CategoryID> categories;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
 */