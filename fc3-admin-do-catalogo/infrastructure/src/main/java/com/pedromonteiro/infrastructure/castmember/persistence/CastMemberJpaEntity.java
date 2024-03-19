package com.pedromonteiro.infrastructure.castmember.persistence;

import java.time.Instant;

import com.pedromonteiro.domain.castmember.CastMember;
import com.pedromonteiro.domain.castmember.CastMemberID;
import com.pedromonteiro.domain.castmember.CastMemberType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "CastMember")
@Table(name = "cast_members")
public class CastMemberJpaEntity {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CastMemberType type;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public CastMemberJpaEntity() {
    }

    public CastMemberJpaEntity(
            final String id,
            final String name,
            final CastMemberType type,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CastMemberJpaEntity from(final CastMember aMember) {
        return new CastMemberJpaEntity(
                aMember.getId().getValue(),
                aMember.getName(),
                aMember.getType(),
                aMember.getCreatedAt(),
                aMember.getUpdatedAt()
        );
    }

    public CastMember toAggregate() {
        return CastMember.with(
                CastMemberID.from(getId()),
                getName(),
                getType(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public CastMemberJpaEntity setId(final String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CastMemberJpaEntity setName(final String name) {
        this.name = name;
        return this;
    }

    public CastMemberType getType() {
        return type;
    }

    public CastMemberJpaEntity setType(final CastMemberType type) {
        this.type = type;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public CastMemberJpaEntity setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public CastMemberJpaEntity setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}