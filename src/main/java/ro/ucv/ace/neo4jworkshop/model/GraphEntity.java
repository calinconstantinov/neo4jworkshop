package ro.ucv.ace.neo4jworkshop.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class GraphEntity {

    @Index(unique = true)
    @EqualsAndHashCode.Include
    String uuid;

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    Long id;

    @CreatedDate
    @Setter(AccessLevel.NONE)
    Instant createdDate;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    Instant lastModifiedDate;
}
