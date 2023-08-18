package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.Exception.ValueNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("controller")
class IndexControllerTest {

  private IndexController controller;

  @BeforeEach
  void setUp() {
    controller = new IndexController();
  }

  @DisplayName("Test Proper View name is returned for index page")
  @Test
  void index() {
    assertEquals("index", controller.index());
    assertEquals("index", controller.index(), "Wrong View Returned");
    // assertion using assertj
    assertThat(controller.index()).isEqualTo("index");
  }

  @Test
  @DisplayName(value = "Test exception")
  void oupsHandler() {
    assertThrows(ValueNotFoundException.class, () -> controller.oopsHandler());
  }

  @Disabled(value = "Demo for timeout")
  @Test
  void testTimeout() {
    assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(2000));
    System.out.println("I got here");
  }

  @Disabled(value = "Demo for timeout")
  @Test
  void testTimeoutPrempt() {
    assertTimeoutPreemptively(Duration.ofMillis(100),() -> Thread.sleep(2000));
    System.out.println("I got here in ....");
  }

  @Test
  void testAssumptionTrue() {
    assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
  }

  @Test
  void testAssumptionTruePositive() {
    assumeTrue("GURU".equalsIgnoreCase("GURU"));
  }

  @EnabledOnOs(OS.MAC)
  @Test
  void testMeOnMacOs(){
  }

  @EnabledOnOs(OS.WINDOWS)
  @Test
  void testMeOnWindows(){
  }

  @EnabledOnJre(JRE.JAVA_8)
  @Test
  void testMeOnJava8(){
  }

  @EnabledOnJre(JRE.OTHER)
  @Test
  void testMeOnJava17(){
  }

  @EnabledIfEnvironmentVariable(named = "USER", matches = "oi")
  @Test
  void testIfUserOI(){
  }

  @EnabledIfEnvironmentVariable(named = "USER", matches = "fred")
  @Test
  void testIfUserFred(){
  }
}