package io.wiklandia.demoeav.demo.data;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AttrRepository extends BaseRepository<Attr> {
    Optional<Attr> findByName(String name);

    boolean existsByName(String name);
}
