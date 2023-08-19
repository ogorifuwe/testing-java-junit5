package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

  @Mock
  private SpecialtyRepository specialtyRepository;

  @InjectMocks
  SpecialitySDJpaService specialitySDJpaService;

  @Test
  void deleteByIdTest() {
    specialitySDJpaService.deleteById(1l);
    specialitySDJpaService.deleteById(1l);

    verify(specialtyRepository, times(2)).deleteById(1l);
  }

  @Test
  void deleteByIdAtLeastTest() {
    specialitySDJpaService.deleteById(1l);
    specialitySDJpaService.deleteById(1l);

    verify(specialtyRepository, atLeastOnce()).deleteById(1l);
  }

  @Test
  void deleteByIdAtMostTest() {
    specialitySDJpaService.deleteById(1l);
    specialitySDJpaService.deleteById(1l);

    verify(specialtyRepository, atMost(5)).deleteById(1l);
  }

  @Test
  void deleteByIdNevertTest() {
    specialitySDJpaService.deleteById(1l);
    specialitySDJpaService.deleteById(1l);

    verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    verify(specialtyRepository, never()).deleteById(5l);
  }

  @Test
  void delete() {
    specialitySDJpaService.delete(new Speciality());
  }
}