package io.wiklandia.demoeav.demo.data;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface EavRepository extends BaseRepository<Eav> {
    Optional<Eav> findByEntAndAttr(Ent ent, Attr attr);

    void deleteByEntAndAttr(Ent ent, Attr attr);

    List<Eav> findByEnt(Ent ent);

}
