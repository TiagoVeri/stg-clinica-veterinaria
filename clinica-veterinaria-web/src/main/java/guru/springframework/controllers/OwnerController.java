package guru.springframework.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.model.Owner;
import guru.springframework.services.OwnerService;

@Controller
@RequestMapping("/owners")
public class OwnerController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	private final OwnerService ownerService;
	
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	//Para dificultar que as pessoas saibam os IDs dos cadastros.
	//Ou coloquem seus IDs e atrapalhem o DB
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		
		return "owners/findOwners";
	}
	
	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		//permite que Get Request sem parametros de /owners retornem todos os registros
		if(owner.getLastName()==null) {
			owner.setLastName(""); //strings vazias significam busca mais ampla possível
		}
		
		//Ache owners pelo lastName
		List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
		
		if(results.isEmpty()) {
			//owners não encontrado
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}
		else if (results.size() == 1) {
			//1 owner encontrado
			owner = results.get(0);
			return"redirect:/owners/" + owner.getId();
		}
		else {
			//multiplos owners encontrados
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}
	
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}
	
	@GetMapping("/new")
	public String initCriationForm(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		if(result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}
	
	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
		model.addAttribute(ownerService.findById(ownerId));
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
		if(result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}
}
