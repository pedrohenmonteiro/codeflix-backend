package com.pedromonteiro.infrastructure.castmember;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.pedromonteiro.domain.castmember.CastMember;
import com.pedromonteiro.domain.castmember.CastMemberGateway;
import com.pedromonteiro.domain.castmember.CastMemberID;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.domain.pagination.SearchQuery;
import com.pedromonteiro.infrastructure.castmember.persistence.CastMemberJpaEntity;
import com.pedromonteiro.infrastructure.castmember.persistence.CastMemberRepository;
import com.pedromonteiro.infrastructure.utils.SpecificationUtils;

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
    public void deleteById(CastMemberID aMemberId) {
        final var anId = aMemberId.getValue();
        if (this.castMemberRepository.existsById(anId)) {
            this.castMemberRepository.deleteById(anId);
        }
    }

    @Override
    public Optional<CastMember> findById(CastMemberID anId) {
        return this.castMemberRepository.findById(anId.getValue())
            .map(CastMemberJpaEntity::toAggregate);
    }

    @Override
    public CastMember update(CastMember aCastMember) {
        return save(aCastMember);
    }

    @Override
    public Pagination<CastMember> findAll(SearchQuery aQuery) {
       final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var where = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =
                this.castMemberRepository.findAll(where, page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CastMemberJpaEntity::toAggregate).toList()
        );
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

       private Specification<CastMemberJpaEntity> assembleSpecification(final String terms) {
        return SpecificationUtils.like("name", terms);
    }
}