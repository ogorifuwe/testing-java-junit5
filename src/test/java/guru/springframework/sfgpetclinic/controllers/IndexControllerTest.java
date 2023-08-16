package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

  private IndexController controller;

  @BeforeEach
  void setUp() {
    controller = new IndexController();
  }

  @Test
  void index() {
    assertEquals("index", controller.index());
    assertEquals("index", controller.index(), "Wrong View Returned");
  }

  @Test
  void oupsHandler() {
    assertTrue("notimplemented".equals(controller.oupsHandler()), () -> "This is an expensive " +
            "Message to build for my test");
  }
}