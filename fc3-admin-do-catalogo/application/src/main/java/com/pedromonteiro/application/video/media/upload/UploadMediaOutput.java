package com.pedromonteiro.application.video.media.upload;

import com.pedromonteiro.domain.video.Video;
import com.pedromonteiro.domain.video.VideoMediaType;

public record UploadMediaOutput(
        String videoId,
        VideoMediaType mediaType
) {

    public static UploadMediaOutput with(final Video aVideo, final VideoMediaType aType) {
        return new UploadMediaOutput(aVideo.getId().getValue(), aType);
    }
}
