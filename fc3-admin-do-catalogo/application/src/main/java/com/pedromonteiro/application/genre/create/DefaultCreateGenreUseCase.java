package com.pedromonteiro.application.genre.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.pedromonteiro.domain.category.CategoryGateway;
import com.pedromonteiro.domain.category.CategoryID;
import com.pedromonteiro.domain.exceptions.NotificationException;
import com.pedromonteiro.domain.genre.Genre;
import com.pedromonteiro.domain.genre.GenreGateway;
import com.pedromonteiro.domain.validation.Error;
import com.pedromonteiro.domain.validation.ValidationHandler;
import com.pedromonteiro.domain.validation.handler.Notification;

public class DefaultCreateGenreUseCase extends CreateGenreUseCase {

    private final CategoryGateway categoryGateway;
    private final GenreGateway genreGateway;

    public DefaultCreateGenreUseCase(
        final GenreGateway genreGateway,
        final CategoryGateway categoryGateway
    ) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateGenreOutput execute(CreateGenreCommand aCommand) {

        final var aName = aCommand.name();
        final var isActive = aCommand.isActive();
        final var categories = toCategoryID(aCommand.categories());


        //notification pattern pra devolver todos erros de uma vez.
        final var notification = Notification.create();
        notification.append(validateCategories(categories));
        final var aGenre = notification.validate(() -> Genre.newGenre(aName, isActive));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Genre", notification);
        }


        return CreateGenreOutput.from(genreGateway.create(aGenre));

    }

    private ValidationHandler validateCategories(final List<CategoryID> ids) {
        final var notification = Notification.create();

        if (ids == null || ids.isEmpty()) {
            return notification;
        }

        final var retrieveIds = categoryGateway.existsByIds(ids);

        if (ids.size() != retrieveIds.size()) {
            // lista remove todos os ids encontrados no banco. portanto contem apenas os ids inexistentes.
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrieveIds);

            final String missingIdsMessage = missingIds.stream()
                .map(CategoryID::getValue)
                .collect(Collectors.joining(", "));

            
            notification.append(new Error("Some categories could not be found: %s".formatted(missingIdsMessage)));
        }


        return notification;
    }


    private final List<CategoryID> toCategoryID(List<String> categories) {
        return categories
            .stream()
            .map(CategoryID::from)
            .toList();
    }
    
}
