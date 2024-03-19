package com.pedromonteiro.infrastructure.castmember;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pedromonteiro.domain.castmember.CastMember;
import com.pedromonteiro.domain.castmember.CastMemberGateway;
import com.pedromonteiro.domain.castmember.CastMemberID;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.pagination.SearchQuery;

@Component
public class CastMemberMySQLGateway implements CastMemberGateway {

    @Override
    public CastMember create(CastMember aCastMember) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void deleteById(CastMemberID anId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Optional<CastMember> findById(CastMemberID anId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public CastMember update(CastMember aCastMember) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Pagination<CastMember> findAll(SearchQuery aQuery) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<CastMemberID> existsByIds(Iterable<CastMemberID> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByIds'");
    }


}