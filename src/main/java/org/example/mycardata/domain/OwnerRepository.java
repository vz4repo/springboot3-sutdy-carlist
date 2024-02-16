package org.example.mycardata.domain;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OwnerRepository extends CrudRepository<Owner, Long> {
  Optional<Owner> findByFirstName(@Param("firstName") String firstName);
}

