package guru.springframework.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="owners")
public class Owner extends Person{

	@Builder
	public Owner(Long id, String firstName, String lastName, String address, String city, String telephone,
			Set<Pet> pets) {
		super(id, firstName, lastName);
		this.address = address;
		this.city = city;
		this.telephone = telephone;
		
		if(pets != null){
			this.pets = pets;
		}
	}

	@Column(name= "address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="telephone")
	private String telephone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="owner")
	private Set<Pet> pets = new HashSet<>();

	/**
	 * Retorna o Pet com o nome nome fornecido, ou null se não for encontrado nenhum para o Owner
	 * @param name para testar
	 * @return true se o nome do pet está em uso
	 */
	public Pet getPet(String name) {
		return getPet(name, false);
	}
	
	/**
	 * Retorna o Pet com o nome fonecido, ou nulo se o não for encontrado para este Owner
	 * @param to test
	 * @return true se o nome do pet estiver em uso
	 */
	public Pet getPet(String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (Pet pet : pets) {
			if(!ignoreNew || !pet.isNew()) {
				String compName = pet.getName();
				compName = compName.toLowerCase();
				if(compName.equals(name)) {
					return pet;
				}
			}
		}
		return null;
	}
	
}
