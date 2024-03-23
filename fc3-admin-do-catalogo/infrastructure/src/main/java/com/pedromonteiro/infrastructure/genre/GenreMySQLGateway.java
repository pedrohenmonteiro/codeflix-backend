package com.pedromonteiro.infrastructure.genre;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.pedromonteiro.domain.genre.Genre;
import com.pedromonteiro.domain.genre.GenreGateway;
import com.pedromonteiro.domain.genre.GenreID;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.pagination.SearchQuery;
import com.pedromonteiro.infrastructure.genre.persistence.GenreJpaEntity;
import com.pedromonteiro.infrastructure.genre.persistence.GenreRepository;
import com.pedromonteiro.infrastructure.utils.SpecificationUtils;


@Component
public class GenreMySQLGateway implements GenreGateway {

    private final GenreRepository genreRepository;

    

    public GenreMySQLGateway(final GenreRepository genreRepository) {
        this.genreRepository = Objects.requireNonNull(genreRepository);
    }

    @Override
    public Genre create(final Genre aGenre) {
        return save(aGenre);
    }

    @Override
    public void deleteById(final GenreID anId) {
        final var aGenreId = anId.getValue();
        if (this.genreRepository.existsById(aGenreId)) {
            this.genreRepository.deleteById(aGenreId);
        }
    }

    @Override
    public Optional<Genre> findById(final GenreID anId) {
       return this.genreRepository.findById(anId.getValue())
        .map(GenreJpaEntity::toAggregate);
    }

    @Override
    public Genre update(final Genre aGenre) {
        return save(aGenre);
    }

    @Override
    public Pagination<Genre> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(
            aQuery.page(),
            aQuery.perPage(),
            Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
    );

    final var where = Optional.ofNullable(aQuery.terms())
            .filter(str -> !str.isBlank())
            .map(this::assembleSpecification)
            .orElse(null);

    final var pageResult =
            this.genreRepository.findAll(Specification.where(where), page);

    return new Pagination<>(
            pageResult.getNumber(),
            pageResult.getSize(),
            pageResult.getTotalElements(),
            pageResult.map(GenreJpaEntity::toAggregate).toList()
    );
    }

        @Override
    public List<GenreID> existsByIds(final Iterable<GenreID> genreIDS) {
        final var ids = StreamSupport.stream(genreIDS.spliterator(), false)
                .map(GenreID::getValue)
                .toList();
        return this.genreRepository.existsByIds(ids).stream()
                .map(GenreID::from)
                .toList();
    }
    
    private Genre save(final Genre aGenre) {
        return this.genreRepository.save(GenreJpaEntity.from(aGenre)).toAggregate();
    }


    private Specification<GenreJpaEntity> assembleSpecification(final String terms) {
        return SpecificationUtils.like("name", terms);
    }
}
