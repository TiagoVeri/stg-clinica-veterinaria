package guru.springframework.services;

import java.util.Set;

import guru.springframework.model.Owner;

public interface OwnerService {
	
	Owner findByLastName(String lastName);

	Owner findById(Long id);
	
	Owner save(Owner owner);
	
	Set<Owner>findAll();
}
