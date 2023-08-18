package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Owner Map Service Test - ")
class OwnerMapServiceTest {

  private OwnerService ownerMapService;
  private PetTypeService petTypeService;
  private PetService petService;

  @BeforeEach
  void setUp() {
    petTypeService = new PetTypeMapService();
    petService = new PetMapService();
    ownerMapService = new OwnerMapService(petTypeService, petService);
    System.out.println("before each");
  }

  @DisplayName("Verify Zero Owners")
  @Test
  void ownersAreZero() {
    int ownerCount = ownerMapService.findAll().size();

    assertThat(ownerCount).isZero();
  }

  @DisplayName("Pet Type - ")
  @Nested
  class TestCreatedPetTypes {
    @BeforeEach
    void setUp() {
      PetType petType = new PetType(1L, "Dog");
      PetType petType2 = new PetType(2L, "Cat");
      petTypeService.save(petType);
      petTypeService.save(petType2);
      System.out.println("nested before each");
    }

    @Test
    void testPetCount() {
      int petTypeCount = petTypeService.findAll().size();
      assertThat(petTypeCount).isNotZero().isEqualTo(2);
    }

    @DisplayName("Save Owner Tests - ")
    @Test
    void saveOwnersTests() {}

    @DisplayName("Save Owners Tests -")
    @Nested
    class SaveOwnersTests {
      @BeforeEach
      void setUp() {
        ownerMapService.save(new Owner(1L, "Itx", "Ownerlyn"));
        System.out.println("3 level before each ");
      }

      @Test
      void saveOwner() {
        Owner owner = new Owner(2L, "Itx", "Ownerling");
        Owner savedOwner = ownerMapService.save(owner);
        assertThat(savedOwner).isNotNull();
      }

      @DisplayName("Save Owners Tests - ")
      @Nested
      class FindOwnersTests {

        @DisplayName("Find Owner")
        @Test
        void findOwner() {
          Owner foundOwner = ownerMapService.findById(1L);
          assertThat(foundOwner).isNotNull();
        }

        @DisplayName("Find Owner Not Found")
        @Test
        void findOwnerNotFound() {
          Owner foundOwner = ownerMapService.findById(2L);
          assertThat(foundOwner).isNull();
        }
      }
    }
  }

  @DisplayName("Verify Still Zero Owners")
  @Test
  void ownersAreStillZero() {
    int ownerCount = ownerMapService.findAll().size();

    assertThat(ownerCount).isZero();
  }
}