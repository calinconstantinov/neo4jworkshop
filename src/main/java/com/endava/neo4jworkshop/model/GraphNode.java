package com.endava.neo4jworkshop.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.Index;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class GraphNode {

    @Index(unique = true)
    @EqualsAndHashCode.Include
    String uuid;

    @CreatedDate
    @Setter(AccessLevel.NONE)
    Instant createdDate;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    Instant lastModifiedDate;

    @CreatedBy
    @Setter(AccessLevel.NONE)
    String createdBy;

    @LastModifiedBy
    @Setter(AccessLevel.NONE)
    String lastModifiedBy;

    //@Id
    //@GeneratedValue
    @Setter(AccessLevel.NONE)
    Long id;
}
