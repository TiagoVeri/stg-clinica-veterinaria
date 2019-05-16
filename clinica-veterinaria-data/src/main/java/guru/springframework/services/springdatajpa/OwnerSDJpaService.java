package guru.springframework.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.model.Owner;
import guru.springframework.repositories.OwnerRepository;
import guru.springframework.repositories.PetRepository;
import guru.springframework.repositories.PetTypeRepository;
import guru.springframework.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;
	
	public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
			PetTypeRepository petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}
	
	@Override
	public Set<Owner> findAll() {

		Set<Owner> owners = new HashSet<>();
		
		ownerRepository.findAll().forEach(owners :: add);
		return owners;
	}

	@Override
	public Owner findById(Long aLong) {
		
		return ownerRepository.findById(aLong).orElse(null);
	}

	@Override
	public Owner save(Owner object) {
		
		return ownerRepository.save(object);
	}

	@Override
	public void delete(Owner object) {
		
		ownerRepository.delete(object);		
	}

	@Override
	public void deleteById(Long aLong) {
		ownerRepository.deleteById(aLong);
		
	}

	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		return ownerRepository.findAllByLastNameLike(lastName);
	}



}
