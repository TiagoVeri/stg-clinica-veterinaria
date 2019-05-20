package guru.springframework.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.model.Pet;
import guru.springframework.model.Visit;
import guru.springframework.services.PetService;
import guru.springframework.services.VisitService;

@Controller
public class VisitController {

	private final VisitService visitService;
	private final PetService petService;

	public VisitController(VisitService visitService, PetService petService) {
		this.visitService = visitService;
		this.petService = petService;
	}

	@InitBinder
	public void setAllowedFields (WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	/**
	 * Chamado antes de todo e qualquer método com @RequestMapping
	 * 2 objectivos:
	 * - Tercerteza que sempre teremos dados atualizados
	 * - Como não usamos session scope, se assegura que Pet object sempre tem um id
	 * (mesmo que o id não seja parte de um campo de formulário)  
	 * @param petId
	 * @return Pet
	 */
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable Long petId, Map<String, Object> model){
		Pet pet = petService.findById(petId);
		model.put("pet", pet);
		Visit visit = new Visit();
		pet.getVisits().add(visit);
		visit.setPet(pet);
		return visit;
	}
	
	//Spring MVC chama método loadPetWithVisit(..) antes de initNewVisitForm seja chamado
	@GetMapping("/owners/*/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable Long petId, Map<String, Object> model) {
		return "pets/createOrUpdateVisitForm";
	}
	
	//Spring MVC chama método loadPetWithVisit(..) antes de processNewVisitForm seja chamado
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
		
		if(result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		} else {
			visitService.save(visit);
			
			return "redirect:/owners/{ownerId}";
		}
	}
	
}
