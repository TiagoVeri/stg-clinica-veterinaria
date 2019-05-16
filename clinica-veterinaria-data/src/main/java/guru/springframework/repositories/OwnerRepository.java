package guru.springframework.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	Owner findByLastName(String lastName);
	
	List<Owner> findAllByLastNameLike(String lastName);

}
