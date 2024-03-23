package com.pedromonteiro.application.video.retrieve.get;

import java.util.Objects;

import com.pedromonteiro.domain.exceptions.NotFoundException;
import com.pedromonteiro.domain.video.Video;
import com.pedromonteiro.domain.video.VideoGateway;
import com.pedromonteiro.domain.video.VideoID;

public class DefaultGetVideoByIdUseCase extends GetVideoByIdUseCase {

    private final VideoGateway videoGateway;

    public DefaultGetVideoByIdUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Override
    public VideoOutput execute(final String anIn) {
        final var aVideoId = VideoID.from(anIn);
        return this.videoGateway.findById(aVideoId)
                .map(VideoOutput::from)
                .orElseThrow(() -> NotFoundException.with(Video.class, aVideoId));
    }
}
