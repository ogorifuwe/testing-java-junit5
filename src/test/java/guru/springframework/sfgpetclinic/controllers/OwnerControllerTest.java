package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  @Mock
  private OwnerService service;

  @Mock
  private BindingResult bindingResult;

  @Mock
  private Model model;

  @InjectMocks
  private OwnerController controller;

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @BeforeEach
  void setUp() {
    given(service.findAllByLastNameLike(stringArgumentCaptor.capture()))
            .willAnswer(invocation -> {
              List<Owner> owners = new ArrayList<>();

              String name = invocation.getArgument(0);

              if (name.equals("%Hamilton%")){
                owners.add(new Owner(1l, "James", "Hamilton"));
                return owners;
              } else if (name.equals("%DontFindMe%")) {
                return owners;
              } else if (name.equals("%FindMe%")) {
                owners.add(new Owner(1l, "James", "Hamilton"));
                owners.add(new Owner(2l, "J", "H"));
                return owners;
              }

              throw new RuntimeException("Invalid Argument");
            });
  }

  @Test
  void processFindFormWildcardStringTestAnswer() {
    Owner owner = new Owner(1l, "James", "Hamilton");
    String viewName = controller.processFindForm(owner, bindingResult, null);

    verify(service).findAllByLastNameLike(stringArgumentCaptor.capture());
    assertEquals("%Hamilton%", stringArgumentCaptor.getValue());
    assertEquals("redirect:/owners/1", viewName);
  }

  @Test
  void processFindFormWildcardStringTestNotFoundAnswer() {
    Owner owner = new Owner(1l, "James", "DontFindMe");
    String viewName = controller.processFindForm(owner, bindingResult, null);

    verify(service).findAllByLastNameLike(stringArgumentCaptor.capture());
    assertEquals("%DontFindMe%", stringArgumentCaptor.getValue());
    assertEquals("owners/findOwners", viewName);
  }

  @Test
  void processFindFormWildcardStringTestFoundAnswer() {
    Owner owner = new Owner(1l, "James", "FindMe");

    InOrder inOrder = Mockito.inOrder(service, model);

    String viewName = controller.processFindForm(owner, bindingResult, model);

    verify(service).findAllByLastNameLike(stringArgumentCaptor.capture());
    assertEquals("%FindMe%", stringArgumentCaptor.getValue());
    assertEquals("owners/ownersList", viewName);

    // inorder assertions
    inOrder.verify(service).findAllByLastNameLike(anyString());
    inOrder.verify(model).addAttribute(anyString(), anyList());
  }



  @Disabled
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
//    List<Owner> owners = new ArrayList<>();
//    when(service.findAllByLastNameLike(stringArgumentCaptor.capture())).thenReturn(owners);

    Owner owner = new Owner(1l, "James", "Hamilton");
    String viewName = controller.processFindForm(owner, bindingResult, null);

    verify(service).findAllByLastNameLike(stringArgumentCaptor.capture());
    assertEquals("%Hamilton%", stringArgumentCaptor.getValue());
  }

  @Disabled
  @Test
  void processCreationFormHasErrorsTest() {
    when(bindingResult.hasErrors()).thenReturn(true);

    Owner owner = new Owner(1l, "Joe", "Schmo");

    String returnedView = controller.processCreationForm(owner, bindingResult);

    assertEquals("owners/createOrUpdateOwnerForm", returnedView);
  }

  @Disabled
  @Test
  void processCreationFormHasNoErrorsTest() {
    Owner owner = new Owner(5l, "Joe", "Schmo");
    when(service.save(any(Owner.class))).thenReturn(owner);

    String returnedView = controller.processCreationForm(owner, bindingResult);

    assertEquals("redirect:/owners/5", returnedView);
  }
}