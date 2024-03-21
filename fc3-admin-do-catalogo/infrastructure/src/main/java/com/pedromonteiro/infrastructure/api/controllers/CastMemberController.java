package com.pedromonteiro.infrastructure.api.controllers;

import org.springframework.http.ResponseEntity;

import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.infrastructure.api.CastMemberAPI;
import com.pedromonteiro.infrastructure.castmember.models.CastMemberListResponse;
import com.pedromonteiro.infrastructure.castmember.models.CastMemberResponse;
import com.pedromonteiro.infrastructure.castmember.models.CreateCastMemberRequest;
import com.pedromonteiro.infrastructure.castmember.models.UpdateCastMemberRequest;

public class CastMemberController implements CastMemberAPI {

    @Override
    public ResponseEntity<?> create(CreateCastMemberRequest input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Pagination<CastMemberListResponse> list(String search, int page, int perPage, String sort,
            String direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public CastMemberResponse getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseEntity<?> updateById(String id, UpdateCastMemberRequest aBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateById'");
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    } 
    
}
