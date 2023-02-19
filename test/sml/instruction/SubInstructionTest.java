package sml.instruction;

import org.junit.jupiter.api.*;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class SubInstructionTest {
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
  @Test
  void labelValid() {
    Instruction instruction = new SubInstruction("aLabel", EDI, EDI);
    Assertions.assertEquals("aLabel", instruction.getLabel());
    }

  @Test
  void OPCodeValid() {
    Instruction instruction = new SubInstruction(null, EDI, EDI);
    Assertions.assertEquals("sub", instruction.getOpcode());
    }


  @Test
  void executeValid() {
    registers.set(EAX, 8);
    registers.set(EBX, 6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(2, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidWithNegativeResultRegister() {
    registers.set(EAX, -10);
    registers.set(EBX, 6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-16, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidWithNegativeSourceRegister() {
    registers.set(EAX, 5);
    registers.set(EBX, -6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidWithBothNegativeRegisters() {
    registers.set(EAX, -9);
    registers.set(EBX, -6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-3, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidWithZeroValue() {
    registers.set(EAX, 0);
    registers.set(EBX,8);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-8, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidWithBothZeroValues() {
    registers.set(EAX, 0);
    registers.set(EBX,0);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidWithSameSourceAndResultRegister() {
    registers.set(EAX, 6);
    Instruction instruction = new SubInstruction(null, EAX, EAX);
    instruction.execute(machine);
    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
  }

  @Test
  void toStringWithoutLabelValid() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.toString();
    Assertions.assertEquals("sub EAX EBX", instruction.toString());
  }

  @Test
  void toStringWithLabelValid() {
    registers.set(ECX, 4);
    registers.set(ESI, 2);
    Instruction instruction = new SubInstruction("labelOne", ECX, ESI);
    instruction.toString();
    Assertions.assertEquals("labelOne: sub ECX ESI", instruction.toString());
  }

  @Test
  void returnValueValid() {
    registers.set(EAX, 3);
    registers.set(EBP, 2);
    Instruction instruction = new SubInstruction(null, EAX, EBP);
    int returnValue = instruction.execute(machine);
    Assertions.assertEquals(-1, returnValue);
  }

  @Nested
  class TestHashCodeAndEquals {
    private Instruction instructionOne;

    @BeforeEach
    void setup() {
      registers.set(EAX, 1);
      registers.set(EBX, 2);
      registers.set(ECX, 3);
      instructionOne = new SubInstruction("aLabel", EAX, EBX);
    }

    @Test
    void equalsTrueWithSameInstructionObject() {
      Assertions.assertTrue(instructionOne.equals(instructionOne));
    }

    @Test
    void equalsTrueWithSameValues() {
      Instruction instructionTwo = new SubInstruction("aLabel", EAX, EBX);
      Assertions.assertTrue(instructionOne.equals(instructionTwo));
      Assertions.assertTrue(instructionTwo.equals(instructionOne));
    }

    @Test
    void equalsFalseWithRegisterChange() {
      Instruction instructionThree = new SubInstruction("aLabel", EAX, ECX);
      Assertions.assertFalse(instructionOne.equals(instructionThree));
      Assertions.assertFalse(instructionThree.equals(instructionOne));
    }

    @Test
    void equalsFalseWithLabelChange() {
      Instruction instructionThree = new SubInstruction("aLabel", EAX, ECX);
      Instruction instructionFour = new SubInstruction(null, EAX, EBX);
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
    void hashCodeForSameInstruction() {
      Assertions.assertEquals(instructionOne.hashCode(), instructionOne.hashCode());
    }

    @Test
    void hashCodeForEqualValues() {
      Instruction instructionTwo = new SubInstruction("aLabel", EAX, EBX);
      Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
    }

    @Test
    void hashCodeNotEqualWithRegisterChange() {
      Instruction instructionThree = new SubInstruction("aLabel", EAX, ECX);
      Assertions.assertNotEquals(instructionOne.hashCode(), instructionThree.hashCode());
    }

    @Test
    void hashCodeNotEqualWithLabelChange() {
      Instruction instructionFour = new SubInstruction(null, EAX, EBX);
      Assertions.assertNotEquals(instructionOne.hashCode(), instructionFour.hashCode());
    }

    @Test
    void hashCodeNotEqualWithNull() {
      Assertions.assertNotEquals((Integer) null, instructionOne.hashCode());
    }

    @Test
    void hashCodeNotEqualWithObject() {
      Object object = new Object();
      Assertions.assertNotEquals(object.hashCode(), instructionOne.hashCode());
    }
  }
}