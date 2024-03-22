package com.pedromonteiro.domain.video;

import java.util.Optional;

import com.pedromonteiro.domain.pagination.Pagination;

public interface VideoGateway {

    Video create(Video aVideo);

    void deleteById(VideoID anId);

    Optional<Video> findById(VideoID anId);

    Video update(Video aVideo);

    Pagination<Video> findAll(VideoSearchQuery aQuery);

}
