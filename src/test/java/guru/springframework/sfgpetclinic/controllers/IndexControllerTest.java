package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.Exception.ValueNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

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
}