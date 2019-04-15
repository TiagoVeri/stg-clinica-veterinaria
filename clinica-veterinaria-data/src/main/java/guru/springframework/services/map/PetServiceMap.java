package guru.springframework.services.map;


import java.util.Set;

import guru.springframework.model.Pet;
import guru.springframework.services.CrudService;
import guru.springframework.services.PetService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService{

	@Override
	public Set<Pet> findAll(){
		return super.findAll();
	}
	
	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Pet save(Pet object) {
		return super.save(object.getId(), object);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
	
	@Override
	public void delete (Pet object) {
		super.delete(object);
	}


}
