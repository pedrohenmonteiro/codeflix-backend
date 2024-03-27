package com.pedromonteiro.infrastructure.video;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.pedromonteiro.domain.Identifier;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.video.Video;
import com.pedromonteiro.domain.video.VideoGateway;
import com.pedromonteiro.domain.video.VideoID;
import com.pedromonteiro.domain.video.VideoPreview;
import com.pedromonteiro.domain.video.VideoSearchQuery;
import com.pedromonteiro.infrastructure.utils.SqlUtils;
import com.pedromonteiro.infrastructure.video.persistence.VideoJpaEntity;
import com.pedromonteiro.infrastructure.video.persistence.VideoRepository;

import static com.pedromonteiro.domain.utils.CollectionUtils.mapTo;
import static com.pedromonteiro.domain.utils.CollectionUtils.nullIfEmpty;

import jakarta.transaction.Transactional;

public class DefaultVideoGateway implements VideoGateway {

    private final VideoRepository videoRepository;

    public DefaultVideoGateway(
            final VideoRepository videoRepository
    ) {
        this.videoRepository = Objects.requireNonNull(videoRepository);
    }

    @Override
    @Transactional
    public Video create(Video aVideo) {
        return save(aVideo);
    }

    @Override
    public void deleteById(VideoID anId) {
        final var aVideoId = anId.getValue();

        if (this.videoRepository.existsById(aVideoId)) {
            this.videoRepository.deleteById(aVideoId);
        }
    }

    @Override
    @Transactional
    public Optional<Video> findById(final VideoID anId) {
        return this.videoRepository.findById(anId.getValue())
                .map(VideoJpaEntity::toAggregate);
    }

    @Override
    @Transactional
    public Video update(Video aVideo) {
        return save(aVideo);
    }

     @Override
    public Pagination<VideoPreview> findAll(final VideoSearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var actualPage = this.videoRepository.findAll(
                SqlUtils.like(SqlUtils.upper(aQuery.terms())),
                nullIfEmpty(mapTo(aQuery.castMembers(), Identifier::getValue)),
                nullIfEmpty(mapTo(aQuery.categories(), Identifier::getValue)),
                nullIfEmpty(mapTo(aQuery.genres(), Identifier::getValue)),
                page
        );

        return new Pagination<>(
                actualPage.getNumber(),
                actualPage.getSize(),
                actualPage.getTotalElements(),
                actualPage.toList()
        );
    }
 
    
     private Video save(final Video aVideo) {
        final var result = this.videoRepository.save(VideoJpaEntity.from(aVideo))
                .toAggregate();

        return result;
    }
}
