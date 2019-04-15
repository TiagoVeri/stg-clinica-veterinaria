package guru.springframework.services.map;


import java.util.Set;

import guru.springframework.model.Vet;
import guru.springframework.services.CrudService;
import guru.springframework.services.VetService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService{

	@Override
	public Set<Vet> findAll(){
		return super.findAll();
	}
	
	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Vet save(Vet object) {
		return super.save(object.getId(), object);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
	
	@Override
	public void delete (Vet object) {
		super.delete(object);
	}


}
