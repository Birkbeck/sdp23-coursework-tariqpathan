package sml.instruction;

import org.junit.jupiter.api.*;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MovInstructionTest {
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
        Instruction instruction = new MovInstruction("aLabel", EDI, 6);
        Assertions.assertEquals("aLabel", instruction.getLabel());
    }

    @Test
    void OPCodeValid() {
        Instruction instruction = new MovInstruction(null, EDI, 1);
        Assertions.assertEquals("mov", instruction.getOpcode());
    }


    @Test
    void executeValid() {
        Instruction instruction = new MovInstruction(null, EAX, 3);
        instruction.execute(machine);
        Assertions.assertEquals(3, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidWithNegativeSource() {
        Instruction instruction = new MovInstruction(null, EAX, -10);
        instruction.execute(machine);
        Assertions.assertEquals(-10, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidWithZeroValueSource() {
        Instruction instruction = new MovInstruction(null, EAX, 0);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void toStringWithoutLabelValid() {
        Instruction instruction = new MovInstruction(null, EAX, 2);
        Assertions.assertEquals("mov EAX 2", instruction.toString());
    }

    @Test
    void toStringWithLabelValid() {
        Instruction instruction = new MovInstruction("labelOne", ECX, 2);
        Assertions.assertEquals("labelOne: mov ECX 2", instruction.toString());
    }

    @Test
    void returnValueValid() {
        Instruction instruction = new MovInstruction(null, EAX, 3);
        int returnValue = instruction.execute(machine);
        Assertions.assertEquals(-1, returnValue);
    }

    @Nested
    class TestHashCodeAndEquals {
        private Instruction instructionOne;

        @BeforeEach
        void setupHashCodeAndEquals() {
            instructionOne = new MovInstruction("aLabel", EAX, 2);
        }

        @AfterEach
        void teardownHashCodeAndEquals() {
            instructionOne = null;
        }

        @Test
        void equalsTrueWithSameInstructionObject() {
            Assertions.assertTrue(instructionOne.equals(instructionOne));
        }

        @Test
        void equalsTrueWithSameValues() {
            Instruction instructionTwo = new MovInstruction("aLabel", EAX, 2);
            Assertions.assertTrue(instructionOne.equals(instructionTwo));
            Assertions.assertTrue(instructionTwo.equals(instructionOne));
        }

        @Test
        void equalsFalseWithRegisterChange() {
            Instruction instructionThree = new MovInstruction("aLabel", EAX, 3);
            Assertions.assertFalse(instructionOne.equals(instructionThree));
            Assertions.assertFalse(instructionThree.equals(instructionOne));
        }

        @Test
        void equalsFalseWithLabelChange() {
            Instruction instructionThree = new MovInstruction("aLabel", EAX, 3);
            Instruction instructionFour = new MovInstruction(null, EAX, 3);
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
            Instruction instructionTwo = new MovInstruction("aLabel", EAX, 2);
            Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
        }

        @Test
        void hashCodeNotEqualWithRegisterChange() {
            Instruction instructionThree = new MovInstruction("aLabel", EAX, 3);
            Assertions.assertNotEquals(instructionOne.hashCode(), instructionThree.hashCode());
        }

        @Test
        void hashCodeNotEqualWithLabelChange() {
            Instruction instructionFour = new MovInstruction(null, EAX, 2);
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
