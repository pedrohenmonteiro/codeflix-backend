package com.pedromonteiro.application.video.media.update;

import static com.pedromonteiro.domain.video.VideoMediaType.TRAILER;
import static com.pedromonteiro.domain.video.VideoMediaType.VIDEO;

import java.util.Objects;

import com.pedromonteiro.domain.exceptions.NotFoundException;
import com.pedromonteiro.domain.video.AudioVideoMedia;
import com.pedromonteiro.domain.video.MediaStatus;
import com.pedromonteiro.domain.video.Video;
import com.pedromonteiro.domain.video.VideoGateway;
import com.pedromonteiro.domain.video.VideoID;
import com.pedromonteiro.domain.video.VideoMediaType;

public class DefaultUpdateMediaStatusUseCase extends UpdateMediaStatusUseCase {

    private final VideoGateway videoGateway;

    public DefaultUpdateMediaStatusUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Override
    public void execute(final UpdateMediaStatusCommand aCmd) {
        final var anId = VideoID.from(aCmd.videoId());
        final var aResourceId = aCmd.resourceId();
        final var folder = aCmd.folder();
        final var filename = aCmd.filename();

        final var aVideo = this.videoGateway.findById(anId)
                .orElseThrow(() -> notFound(anId));

        final var encodedPath = "%s/%s".formatted(folder, filename);

        if (matches(aResourceId, aVideo.getVideo().orElse(null))) {
            updateVideo(VIDEO, aCmd.status(), aVideo, encodedPath);
        } else if (matches(aResourceId, aVideo.getTrailer().orElse(null))) {
            updateVideo(TRAILER, aCmd.status(), aVideo, encodedPath);
        }
    }

    private void updateVideo(final VideoMediaType aType, final MediaStatus aStatus, final Video aVideo, final String encodedPath) {
        switch (aStatus) {
            case PENDING -> {
            }
            case PROCESSING -> aVideo.processing(aType);
            case COMPLETED -> aVideo.completed(aType, encodedPath);
        }

        this.videoGateway.update(aVideo);
    }

    private boolean matches(final String anId, final AudioVideoMedia aMedia) {
        if (aMedia == null) {
            return false;
        }

        return aMedia.id().equals(anId);
    }

    private NotFoundException notFound(final VideoID anId) {
        return NotFoundException.with(Video.class, anId);
    }
}
