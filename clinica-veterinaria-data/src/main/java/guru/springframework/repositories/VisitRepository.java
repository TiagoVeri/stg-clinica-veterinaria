package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.model.Owner;
import guru.springframework.model.Pet;
import guru.springframework.model.Vet;
import guru.springframework.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
