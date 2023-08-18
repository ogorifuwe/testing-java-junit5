package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "This is an expensive " +
            "Message to build for my test");
  }
}