package io.wiklandia.demoeav.demo.data;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface EavRepository extends BaseRepository<Eav> {
    Optional<Eav> findByEntAttr(EntAttr entAttr);

    void deleteByEntAttr(EntAttr entAttr);

}
