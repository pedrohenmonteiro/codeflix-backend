package com.pedromonteiro.infrastructure.video;

import java.util.Objects;
import java.util.Optional;

import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.video.Video;
import com.pedromonteiro.domain.video.VideoGateway;
import com.pedromonteiro.domain.video.VideoID;
import com.pedromonteiro.domain.video.VideoSearchQuery;
import com.pedromonteiro.infrastructure.video.persistence.VideoJpaEntity;
import com.pedromonteiro.infrastructure.video.persistence.VideoRepository;

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
    public Pagination<Video> findAll(VideoSearchQuery aQuery) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
 
    
     private Video save(final Video aVideo) {
        final var result = this.videoRepository.save(VideoJpaEntity.from(aVideo))
                .toAggregate();

        return result;
    }
}
