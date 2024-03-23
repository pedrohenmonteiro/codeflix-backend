package com.pedromonteiro.application.video.retrieve.list;

import com.pedromonteiro.application.UseCase;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.video.VideoSearchQuery;

public abstract class ListVideosUseCase
        extends UseCase<VideoSearchQuery, Pagination<VideoListOutput>> {
}
