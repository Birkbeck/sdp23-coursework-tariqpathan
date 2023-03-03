package sml.instruction;

import org.junit.jupiter.api.*;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.*;

class OutInstructionTest {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Machine machine;
    private Registers registers;
    private PrintStream originalSystemOut;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(originalSystemOut);
    }

    @Test
    void labelValid() {
        Instruction instruction = new OutInstruction("aLabel", EDI);
        Assertions.assertEquals("aLabel", instruction.getLabel());
    }

    @Test
    void OPCodeValid() {
        Instruction instruction = new OutInstruction(null, EDI);
        Assertions.assertEquals("out", instruction.getOpcode());
    }


    @Test
    void executeValid() {
        machine.getRegisters().set(EAX, 3);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals("EAX: 3", outputStream.toString().trim());
    }

    @Test
    void executeValidWithNegativeSource() {
        machine.getRegisters().set(EAX, -3);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals("EAX: -3", outputStream.toString().trim());
    }

    @Test
    void executeValidWithZeroValueSource() {
        machine.getRegisters().set(EAX, 0);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals("EAX: 0", outputStream.toString().trim());
    }

    @Test
    void toStringWithoutLabelValid() {
        Instruction instruction = new OutInstruction(null, EAX);
        Assertions.assertEquals("out EAX", instruction.toString());
    }

    @Test
    void toStringWithLabelValid() {
        Instruction instruction = new OutInstruction("labelOne", ECX);
        Assertions.assertEquals("labelOne: out ECX", instruction.toString());
    }

    @Test
    void returnValueValid() {
        Instruction instruction = new OutInstruction(null, EAX);
        int returnValue = instruction.execute(machine);
        Assertions.assertEquals(-1, returnValue);
    }

    @Nested
    class TestHashCodeAndEquals {
        private Instruction instructionOne;

        @BeforeEach
        void setupHashCodeAndEquals() {
            instructionOne = new OutInstruction("aLabel", EAX);
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
            Instruction instructionTwo = new OutInstruction("aLabel", EAX);
            Assertions.assertTrue(instructionOne.equals(instructionTwo));
            Assertions.assertTrue(instructionTwo.equals(instructionOne));
        }

        @Test
        void equalsFalseWithRegisterChange() {
            Instruction instructionThree = new OutInstruction("aLabel", EBX);
            Assertions.assertFalse(instructionOne.equals(instructionThree));
            Assertions.assertFalse(instructionThree.equals(instructionOne));
        }

        @Test
        void equalsFalseWithLabelChange() {
            Instruction instructionFour = new OutInstruction(null, EAX);
            Assertions.assertFalse(instructionOne.equals(instructionFour));
            Assertions.assertFalse(instructionFour.equals(instructionOne));
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
            Instruction instructionTwo = new OutInstruction("aLabel", EAX);
            Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
        }

        @Test
        void hashCodeNotEqualWithRegisterChange() {
            Instruction instructionThree = new OutInstruction("aLabel", EBX);
            Assertions.assertNotEquals(instructionOne.hashCode(), instructionThree.hashCode());
        }

        @Test
        void hashCodeNotEqualWithLabelChange() {
            Instruction instructionFour = new OutInstruction(null, EAX);
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
