package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

  @Mock
  private VisitRepository repository;

  @InjectMocks
  private VisitSDJpaService service;

  @Test
  void findAll() {
    Set<Visit> visits = new HashSet<>();
    visits.add(new Visit(1l, LocalDate.now()));
    visits.add(new Visit(2l, LocalDate.now()));

    when(repository.findAll()).thenReturn(visits);

    Set<Visit> foundVisits = service.findAll();

    assertThat(foundVisits).isNotNull();
    assertThat(foundVisits).hasSize(2);
    verify(repository).findAll();
  }

  @Test
  void findById() {
    Visit v = new Visit();

    when(repository.findById(anyLong())).thenReturn(Optional.of(v));

    Visit foundVisit = service.findById(1l);

    verify(repository).findById(anyLong());
    assertNotNull(foundVisit);
    assertEquals(v, foundVisit);
  }

  @Test
  void save() {
    Visit v = new Visit();

    when(repository.save(any(Visit.class))).thenReturn(v);

    Visit savedVisit = service.save(new Visit());

    verify(repository).save(any(Visit.class));
    assertNotNull(savedVisit);
    assertEquals(v, savedVisit);
  }

  @Test
  void delete() {
    service.delete(new Visit());
    verify(repository).delete(any(Visit.class));
  }

  @Test
  void deleteById() {
    service.deleteById(1L);
    verify(repository).deleteById(anyLong());
  }
}