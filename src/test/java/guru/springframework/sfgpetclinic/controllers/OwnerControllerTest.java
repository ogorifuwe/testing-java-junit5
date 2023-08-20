package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  @Mock
  private OwnerService service;

  @Mock
  private BindingResult bindingResult;

  @InjectMocks
  private OwnerController controller;

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @Test
  void processFindFormWildcardStringTest() {
    List<Owner> owners = new ArrayList<>();
    final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    when(service.findAllByLastNameLike(captor.capture())).thenReturn(owners);

    Owner owner = new Owner(1l, "James", "Hamilton");
    String viewName = controller.processFindForm(owner, bindingResult, null);

    verify(service).findAllByLastNameLike(captor.capture());
    assertEquals("%Hamilton%", captor.getValue());
  }

  @Test
  void processFindFormWildcardStringTestAnnotationBased() {
    List<Owner> owners = new ArrayList<>();
    when(service.findAllByLastNameLike(stringArgumentCaptor.capture())).thenReturn(owners);

    Owner owner = new Owner(1l, "James", "Hamilton");
    String viewName = controller.processFindForm(owner, bindingResult, null);

    verify(service).findAllByLastNameLike(stringArgumentCaptor.capture());
    assertEquals("%Hamilton%", stringArgumentCaptor.getValue());
  }

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