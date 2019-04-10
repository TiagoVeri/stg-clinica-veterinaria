package guru.springframework.services;

import java.util.Set;

import guru.springframework.model.Vet;

public interface VetService {

	Vet findById(Long id);
	
	Vet save(Vet vet);
	
	Set<Vet>findAll();
}
