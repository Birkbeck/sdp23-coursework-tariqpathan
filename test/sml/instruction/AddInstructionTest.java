package sml.instruction;

import org.junit.jupiter.api.*;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class AddInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Nested
  class AddInstructionConstructor {
    private Instruction instruction;

    @BeforeEach
    void setUp() {
      instruction = new AddInstruction("aLabel", EAX, EBX);
    }

    @Test
    void labelValid() {
      Assertions.assertEquals("aLabel", instruction.getLabel());
    }

    @Test
    void OPCodeValid() {
      Assertions.assertEquals("add", instruction.getOpcode());
    }
  }

  @Test
  void executeValid() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void toStringWithoutLabelValid() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.toString();
    Assertions.assertEquals("add EAX EBX", instruction.toString());
  }

  @Test
  void toStringWithLabelValid() {
    registers.set(ECX, 4);
    registers.set(ESI, 2);
    Instruction instruction = new AddInstruction("addOne", ECX, ESI);
    instruction.toString();
    Assertions.assertEquals("addOne: add ECX ESI", instruction.toString());
  }

  @Test
  void returnValueValid() {
    registers.set(EAX, 3);
    registers.set(EBP, 2);
    Instruction instruction = new AddInstruction(null, EAX, EBP);
    int returnValue = instruction.execute(machine);
    Assertions.assertEquals(-1, returnValue);
  }

  @Nested
  class TestTwoInstructions {
    private Instruction instructionOne;

    @BeforeEach
    void setup() {
      registers.set(EAX, 1);
      registers.set(EBX, 2);
      registers.set(ECX, 3);
      instructionOne = new AddInstruction("aLabel", EAX, EBX);
    }

    @Test
    void equalsTrueWithSameInstructionObject() {
      Assertions.assertTrue(instructionOne.equals(instructionOne));
    }

    @Test
    void equalsTrueWithSameValues() {
      Instruction instructionTwo = new AddInstruction("aLabel", EAX, EBX);

      Assertions.assertTrue(instructionOne.equals(instructionTwo));
      Assertions.assertTrue(instructionTwo.equals(instructionOne));
    }

    @Test
    void equalsFalseWithRegisterChange() {
      Instruction instructionThree = new AddInstruction("aLabel", EAX, ECX);

      Assertions.assertFalse(instructionOne.equals(instructionThree));
      Assertions.assertFalse(instructionThree.equals(instructionOne));
    }

    @Test
    void equalsFalseWithLabelChange() {
      Instruction instructionThree = new AddInstruction("aLabel", EAX, ECX);
      Instruction instructionFour = new AddInstruction(null, EAX, EBX);
      Assertions.assertFalse(instructionThree.equals(instructionFour));
      Assertions.assertFalse(instructionFour.equals(instructionThree));
    }

    @Test
    void equalsFalseWithNull() {
      Assertions.assertFalse(instructionOne.equals(null));
    }

    @Test
    void equalsFalseWithObject() {
      Object object = new Object();
      Assertions.assertFalse(instructionOne.equals(object));
    }

    @Test
    void hashCodeForSameInstructionValid() {
      Assertions.assertEquals(instructionOne.hashCode(), instructionOne.hashCode());
    }

    @Test
    void hashCodeForEqualValuesValid() {
      Instruction instructionTwo = new AddInstruction("aLabel", EAX, EBX);
      Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    @Test
    void hashCodeNotEqualWithRegisterChangeValid() {
      Instruction instructionThree = new AddInstruction("aLabel", EAX, ECX);
      Assertions.assertNotEquals(instructionOne.hashCode(), instructionThree.hashCode());
    }

    @Test
    void hashCodeNotEqualWithLabelChangeValid() {
      Instruction instructionFour = new AddInstruction(null, EAX, EBX);
      Assertions.assertNotEquals(instructionOne.hashCode(), instructionFour.hashCode());
    }

    @Test
    void hashCodeNotEqualWithNullValid() {
      Assertions.assertNotEquals((Integer) null, instructionOne.hashCode());
    }

    @Test
    void hashCodeNotEqualWithObjectValid() {
      Object object = new Object();
      Assertions.assertNotEquals(object.hashCode(), instructionOne.hashCode());
    }
  }
}