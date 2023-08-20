package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  @Mock
  private OwnerService service;

  @Mock
  private BindingResult bindingResult;

  @InjectMocks
  private OwnerController controller;

  @Test
  void processCreationFormHasErrorsTest() {
    when(bindingResult.hasErrors()).thenReturn(true);

    Owner owner = new Owner(1l, "Joe", "Schmo");

    String returnedView = controller.processCreationForm(owner, bindingResult);

    assertEquals("owners/createOrUpdateOwnerForm", returnedView);
  }

  @Test
  void processCreationFormHasNoErrorsTest() {
    Owner owner = new Owner(5l, "Joe", "Schmo");
    when(service.save(any(Owner.class))).thenReturn(owner);

    String returnedView = controller.processCreationForm(owner, bindingResult);

    assertEquals("redirect:/owners/5", returnedView);
  }
}