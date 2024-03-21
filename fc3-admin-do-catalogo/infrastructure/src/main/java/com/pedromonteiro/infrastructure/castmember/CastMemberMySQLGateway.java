package com.pedromonteiro.infrastructure.castmember;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pedromonteiro.domain.castmember.CastMember;
import com.pedromonteiro.domain.castmember.CastMemberGateway;
import com.pedromonteiro.domain.castmember.CastMemberID;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.pagination.SearchQuery;
import com.pedromonteiro.infrastructure.castmember.persistence.CastMemberJpaEntity;
import com.pedromonteiro.infrastructure.castmember.persistence.CastMemberRepository;

@Component
public class CastMemberMySQLGateway implements CastMemberGateway {

    private final CastMemberRepository castMemberRepository;

    

    public CastMemberMySQLGateway(CastMemberRepository castMemberRepository) {
        this.castMemberRepository = castMemberRepository;
    }

    @Override
    public CastMember create(CastMember aCastMember) {
        return save(aCastMember);
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
        return save(aCastMember);
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

    private CastMember save(CastMember aCastMember) {
        return this.castMemberRepository.save(CastMemberJpaEntity.from(aCastMember))
            .toAggregate();
    }

}