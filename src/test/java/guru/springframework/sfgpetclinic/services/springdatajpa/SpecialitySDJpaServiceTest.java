package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

  @Mock
  private SpecialtyRepository specialtyRepository;

  @InjectMocks
  SpecialitySDJpaService service;

  @Test
  void deleteByObjectTest() {
    Speciality s = new Speciality();

    service.delete(s);

    verify(specialtyRepository).delete(any(Speciality.class));
  }

  @Test
  void findByIdTest() {
    Speciality s = new Speciality();

    when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(s));

    Speciality foundSpecialty = service.findById(1l);

    verify(specialtyRepository).findById(1l);
    assertThat(foundSpecialty).isNotNull();
    assertEquals(s, foundSpecialty);
  }

  @Test
  void deleteByIdTest() {
    service.deleteById(1l);
    service.deleteById(1l);

    verify(specialtyRepository, times(2)).deleteById(1l);
  }

  @Test
  void deleteByIdAtLeastTest() {
    service.deleteById(1l);
    service.deleteById(1l);

    verify(specialtyRepository, atLeastOnce()).deleteById(1l);
  }

  @Test
  void deleteByIdAtMostTest() {
    service.deleteById(1l);
    service.deleteById(1l);

    verify(specialtyRepository, atMost(5)).deleteById(1l);
  }

  @Test
  void deleteByIdNevertTest() {
    service.deleteById(1l);
    service.deleteById(1l);

    verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    verify(specialtyRepository, never()).deleteById(5l);
  }

  @Test
  void delete() {
    service.delete(new Speciality());
  }
}