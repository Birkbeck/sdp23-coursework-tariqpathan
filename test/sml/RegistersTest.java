package sml;

import org.junit.jupiter.api.*;
import static sml.Registers.Register;
import static sml.Registers.Register.*;

class RegistersTest {
  private Machine machine;
  private Registers r;
  // TODO: Test the equals and hashcode methods

  @BeforeEach
  void setUp() {
    r = new Registers();
  }

  @AfterEach
  void tearDown() {
    machine = null;
  }
  @Test
  void setValid() {
    return;
  }

}