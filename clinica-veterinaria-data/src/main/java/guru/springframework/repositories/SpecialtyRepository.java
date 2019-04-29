package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.model.Owner;
import guru.springframework.model.Pet;
import guru.springframework.model.Speciality;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {

}
