package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

  @Mock
  private VisitService visitService;

//  @Mock
//  private PetService petService;

  @Spy
  private PetMapService petService;


  @InjectMocks
  private VisitController controller;

  @Test
  void loadPetWithVisit() {
    Map<String, Object> model = new HashMap<>();
    Pet pet = new Pet(1l);
    petService.save(pet);

//    when(petService.findById(anyLong())).thenReturn(pet);
    when(petService.findById(anyLong())).thenCallRealMethod();

    Visit visit = controller.loadPetWithVisit(1l, model);

    assertNotNull(visit);
    assertNotNull(visit.getPet());
    assertEquals(1l, visit.getPet().getId());

    verify(petService, times(1)).findById(anyLong());
  }

  @Test
  void loadPetWithVisitWithStubbing() {
    Map<String, Object> model = new HashMap<>();
    Pet pet = new Pet(1l);
    Pet pet2 = new Pet(2l);

    petService.save(pet);
    petService.save(pet2);

    when(petService.findById(anyLong())).thenReturn(pet2);

    Visit visit = controller.loadPetWithVisit(1l, model);

    assertNotNull(visit);
    assertNotNull(visit.getPet());
    assertEquals(2l, visit.getPet().getId());

    verify(petService, times(1)).findById(anyLong());
  }
}