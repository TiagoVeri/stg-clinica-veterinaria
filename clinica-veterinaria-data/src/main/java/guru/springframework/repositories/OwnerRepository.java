package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
