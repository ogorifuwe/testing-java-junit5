package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@Tag("model")
class OwnerTest {

  @Test
  void dependentAssertions() {
    Owner owner = new Owner(1l, "Joe", "Buck");
    owner.setCity("Key West");
    owner.setTelephone("1234535353");

    assertAll("Properties Test",
            () -> assertAll("Person Properties",
                    () -> assertEquals("Joe", owner.getFirstName(), "First Name Did not Match"),
                    () -> assertEquals("Buck", owner.getLastName())),
            () -> assertAll( "Owner Properties",
                    () -> assertEquals("Key West", owner.getCity(), "City Did Not Match"),
                    () -> assertEquals("1234535353", owner.getTelephone())
            ));
    // assertion using hamcrest
    assertThat(owner.getCity(), is("Key West"));
  }
}