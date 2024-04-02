package com.pedromonteiro.infrastructure.api.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.infrastructure.api.VideoAPI;
import com.pedromonteiro.infrastructure.video.models.CreateVideoRequest;
import com.pedromonteiro.infrastructure.video.models.UpdateVideoRequest;
import com.pedromonteiro.infrastructure.video.models.VideoListResponse;
import com.pedromonteiro.infrastructure.video.models.VideoResponse;

public class VideoController implements VideoAPI {

    @Override
    public Pagination<VideoListResponse> list(String search, int page, int perPage, String sort, String direction,
            Set<String> castMembers, Set<String> categories, Set<String> genres) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public ResponseEntity<?> createFull(String title, String description, Integer yearLaunched, Double duration,
            Boolean opened, Boolean published, String rating, Set<String> categories, Set<String> castMembers,
            Set<String> genres, MultipartFile videoFile, MultipartFile trailerFile, MultipartFile bannerFile,
            MultipartFile thumbFile, MultipartFile thumbHalfFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFull'");
    }

    @Override
    public ResponseEntity<?> createPartial(CreateVideoRequest payload) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPartial'");
    }

    @Override
    public VideoResponse getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseEntity<?> update(String id, UpdateVideoRequest payload) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public ResponseEntity<byte[]> getMediaByType(String id, String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMediaByType'");
    }

    @Override
    public ResponseEntity<?> uploadMediaByType(String id, String type, MultipartFile media) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadMediaByType'");
    }
    
}
