package sml.instruction;

import org.junit.jupiter.api.*;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;
import static sml.Registers.Register.*;

class JnzInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        machine.getLabels().addLabel("f1", 4);
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void labelValid() {
        Instruction instruction = new JnzInstruction("aLabel", EDI, "f1");
        Assertions.assertEquals("aLabel", instruction.getLabel());
    }

    @Test
    void OPCodeValid() {
        Instruction instruction = new JnzInstruction(null, EDI, "f1");
        Assertions.assertEquals("jnz", instruction.getOpcode());
    }


    @Test
    void executeValid() {
        machine.getRegisters().set(EAX, 3);
        String goToLabel = "f1";
        Instruction instruction = new JnzInstruction(null, EAX, goToLabel);
        int address = machine.getLabels().getAddress(goToLabel);
        Assertions.assertEquals(address, instruction.execute(machine));
    }

    @Test
    void executeValidWithZeroValueSource() {
        machine.getRegisters().set(EAX, 0);
        String goToLabel = "f1";
        Instruction instruction = new JnzInstruction(null, EAX, goToLabel);
        Assertions.assertEquals(NORMAL_PROGRAM_COUNTER_UPDATE, instruction.execute(machine));
    }

    @Test
    void executeInvalidLabel() {
        registers.set(EAX, 5);
        Instruction instruction = new JnzInstruction(null, EAX, "invalid");
        Exception thrown = Assertions.assertThrows(
                NullPointerException.class,
                () -> instruction.execute(machine)
        );
        Assertions.assertEquals("The label: invalid does not exist\n" +
                "Caused by instruction: jnz EAX invalid", thrown.getMessage());
    }


    @Test
    void toStringWithoutLabelValid() {
        Instruction instruction = new JnzInstruction(null, EAX, "f0");
        Assertions.assertEquals("jnz EAX f0", instruction.toString());
    }

    @Test
    void toStringWithLabelValid() {
        Instruction instruction = new JnzInstruction("labelOne", ECX, "f0");
        Assertions.assertEquals("labelOne: jnz ECX f0", instruction.toString());
    }

    @Nested
    class TestHashCodeAndEquals {
        String goToLabelOne;
        private Instruction instructionOne;

        @BeforeEach
        void setupHashCodeAndEquals() {
            String goToLabelOne = "f0";
            instructionOne = new JnzInstruction("aLabel", EAX, goToLabelOne);
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
            String goToLabelTwo = "f0";
            Instruction instructionTwo = new JnzInstruction("aLabel", EAX, goToLabelTwo);
            Assertions.assertTrue(instructionOne.equals(instructionTwo));
            Assertions.assertTrue(instructionTwo.equals(instructionOne));
        }

        @Test
        void equalsFalseWithRegisterChange() {
            Instruction instructionThree = new JnzInstruction("aLabel", EBX, goToLabelOne);
            Assertions.assertFalse(instructionOne.equals(instructionThree));
            Assertions.assertFalse(instructionThree.equals(instructionOne));
        }

        @Test
        void equalsFalseWithLabelChange() {
            Instruction instructionFour = new JnzInstruction(null, EAX, goToLabelOne);
            Assertions.assertFalse(instructionOne.equals(instructionFour));
            Assertions.assertFalse(instructionFour.equals(instructionOne));
        }

        @Test
        void equalsFalseWithGoToLabelChange() {
            String goToLabelFive = "f4";
            Instruction instructionFive = new JnzInstruction("aLabel", EAX, goToLabelFive);
            Assertions.assertFalse(instructionOne.equals(instructionFive));
            Assertions.assertFalse(instructionFive.equals(instructionOne));
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
            String goToLabelTwo = "f0";
            Instruction instructionTwo = new JnzInstruction("aLabel", EAX, goToLabelTwo);
            Assertions.assertEquals(instructionOne.hashCode(), instructionTwo.hashCode());
        }

        @Test
        void hashCodeNotEqualWithRegisterChange() {
            Instruction instructionThree = new JnzInstruction("aLabel", EBX, goToLabelOne);
            Assertions.assertNotEquals(instructionOne.hashCode(), instructionThree.hashCode());
        }

        @Test
        void hashCodeNotEqualWithLabelChange() {
            Instruction instructionFour = new JnzInstruction(null, EAX, goToLabelOne);
            Assertions.assertNotEquals(instructionOne.hashCode(), instructionFour.hashCode());
        }

        @Test
        void hashCodeNotEqualWithGoToLabelChange() {
            String goToLabelFive = "f4";
            Instruction instructionFour = new JnzInstruction("aLabel", EAX, goToLabelFive);
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
