package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.model.Owner;
import guru.springframework.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
