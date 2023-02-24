package sml;

import org.junit.jupiter.api.*;
import sml.Labels;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LabelsTest {
  private Labels labels;

  @BeforeEach
  void setUp() {
    labels = new Labels();
  }

  @AfterEach
  void tearDown() {
    labels.reset();
  }
  @Test
  void addOneLabelValid() {
    labels.addLabel("first", 1);
    Assertions.assertEquals("[first -> 1]", labels.toString());
  }

  @Test
  void addMultipleLabelsValid() {
    for (int i = 1; i < 4; i++) {
      labels.addLabel(String.valueOf(i), i);
    }
    String expected = "[1 -> 1, 2 -> 2, 3 -> 3]";
    Assertions.assertEquals(expected, labels.toString());
  }

  @Test
  void getAddressValidWithCorrectLabel() {
    labels.addLabel("first", 1);
    labels.addLabel("second", 2);
    Assertions.assertEquals(1, labels.getAddress("first"));
    Assertions.assertEquals(2, labels.getAddress("second"));
  }

  @Test
  void getAddressThrowsExceptionWithIncorrectLabel() {
    labels.addLabel("first", 1);
    Exception thrown = Assertions.assertThrows(
            NullPointerException.class, () -> {
              labels.getAddress("second");
            });
    Assertions.assertEquals("no label exists", thrown.getMessage());
  }

  @Test
  void toStringMethodValid() {
    labels.addLabel("first", 1);
    labels.addLabel("fifth", 5);
    labels.addLabel("eighth", 8);
    String expected = "[eighth -> 8, fifth -> 5, first -> 1]";
    Assertions.assertEquals(expected, labels.toString());
  }

  @Nested
  class TestHashCodeAndEquals {
    private Labels labelOne;

    @BeforeEach
    void setupHashCodeAndEquals() {
      labelOne = new Labels();
      labelOne.addLabel("first", 1);
      labelOne.addLabel("second", 2);
    }

    @AfterEach
    void teardownHashCodeAndEquals() {
      labelOne.reset();
    }

    @Test
    void equalsTrueWithSameLabels() {
      Assertions.assertTrue(labelOne.equals(labelOne));
    }

    @Test
    void equalsTrueWithSameLabelsAndRegisters() {
      Labels labelTwo = new Labels();
      labelTwo.addLabel("first", 1);
      labelTwo.addLabel("second", 2);
      Assertions.assertTrue(labelOne.equals(labelTwo));
      Assertions.assertTrue(labelTwo.equals(labelOne));
    }

    @Test
    void equalsFalseWithDifferentLabels() {
      Labels labelTwo = new Labels();
      labelTwo.addLabel("third", 1);
      labelTwo.addLabel("fourth", 2);
      Assertions.assertFalse(labelOne.equals(labelTwo));
      Assertions.assertFalse(labelTwo.equals(labelOne));
    }

    @Test
    void equalsFalseWithDifferentRegisters() {
      Labels labelTwo = new Labels();
      labelTwo.addLabel("first", 3);
      labelTwo.addLabel("second", 4);
      Assertions.assertFalse(labelOne.equals(labelTwo));
      Assertions.assertFalse(labelTwo.equals(labelOne));
    }

    @Test
    void equalsFalseWithNull() {
      Assertions.assertFalse(labelOne.equals(null));
    }

    @Test
    void equalsFalseWithObject() {
      Object object = new Object();
      Assertions.assertFalse(labelOne.equals(object));
    }

    @Test
    void hashCodeEqualWithSameLabels() {
      Assertions.assertEquals(labelOne.hashCode(), labelOne.hashCode());
    }

    @Test
    void hashCodeEqualWithSameLabelsAndRegisters() {
      Labels labelTwo = new Labels();
      labelTwo.addLabel("first", 1);
      labelTwo.addLabel("second", 2);
      Assertions.assertEquals(labelOne.hashCode(), labelTwo.hashCode());
    }

    @Test
    void hashCodeNotEqualWithDifferentLabels() {
      Labels labelTwo = new Labels();
      labelTwo.addLabel("third", 1);
      labelTwo.addLabel("fourth", 2);
      Assertions.assertNotEquals(labelOne.hashCode(), labelTwo.hashCode());
    }

    @Test
    void hashCodeNotEqualWithDifferentRegisters() {
      Labels labelTwo = new Labels();
      labelTwo.addLabel("first", 3);
      labelTwo.addLabel("second", 4);
      Assertions.assertNotEquals(labelOne.hashCode(), labelTwo.hashCode());
    }

    @Test
    void hashCodeNotEqualWithNull(){
      Assertions.assertNotEquals((Integer) null, labelOne.hashCode());
    }

    @Test
    void hashCodeNotEqualWithObject(){
      Object object = new Object();
      Assertions.assertNotEquals(object, labelOne.hashCode());
    }

  }


}