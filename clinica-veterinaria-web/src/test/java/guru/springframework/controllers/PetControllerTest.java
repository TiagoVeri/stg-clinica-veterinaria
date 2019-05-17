package guru.springframework.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.model.Owner;
import guru.springframework.model.PetType;
import guru.springframework.services.OwnerService;
import guru.springframework.services.PetService;
import guru.springframework.services.PetTypeService;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

	@Mock
	PetService petService;
	
	@Mock
	OwnerService ownerService;
	
	@Mock
	PetTypeService petTypeService;
	
	@InjectMocks
	PetController petController; 
	
	MockMvc mockMvc;
	
	Owner owner;
	
	Set<PetType> petTypes;
	
	@BeforeEach
	void setUp() {
		owner = Owner.builder().id(1l).build();
		
		petTypes = new HashSet<>();
		petTypes.add(PetType.builder().id(1L).name("Dog").build());
		petTypes.add(PetType.builder().id(2L).name("Cat").build());

		mockMvc = MockMvcBuilders
				.standaloneSetup(petController)
				.build();
	}
	
	@Test
	void initCreationForm() throws Exception{
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(get("/owners/1/pets/new"))
			   .andExpect(status().isOk())
			   .andExpect(model().attributeExists("owner"))
			   .andExpect(model().attributeExists("pet"))
			   .andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	
	//arrumar
	@Test
	void processCreationForm() throws Exception {
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());
		
		mockMvc.perform(post("/owners/new"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"))
				.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(ArgumentMatchers.any());
	}
}
