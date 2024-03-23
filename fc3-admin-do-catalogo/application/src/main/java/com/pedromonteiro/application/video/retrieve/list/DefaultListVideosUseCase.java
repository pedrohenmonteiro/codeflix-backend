package com.pedromonteiro.application.video.retrieve.list;

import java.util.Objects;

import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.video.VideoGateway;
import com.pedromonteiro.domain.video.VideoSearchQuery;

public class DefaultListVideosUseCase extends ListVideosUseCase {

    private final VideoGateway videoGateway;

    public DefaultListVideosUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Override
    public Pagination<VideoListOutput> execute(final VideoSearchQuery aQuery) {
        return this.videoGateway.findAll(aQuery)
                .map(VideoListOutput::from);
    }
}
