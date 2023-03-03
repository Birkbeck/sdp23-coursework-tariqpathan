package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sml.Registers.Register.*;

class RegistersTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void equalsValid() {
        Assertions.assertTrue(registers.equals(registers));
    }

    @Test
    void equalsValidTwo() {
        Machine machineTwo = new Machine(new Registers());
        Registers registersTwo = machineTwo.getRegisters();
        Assertions.assertTrue(registersTwo.equals(registers));
        Assertions.assertTrue(registers.equals(registersTwo));
    }

    @Test
    void equalsValidThree() {
        Machine machineTwo = new Machine(new Registers());
        Registers registersTwo = machineTwo.getRegisters();
        registers.set(EAX, 3);
        registersTwo.set(EAX, 3);
        Assertions.assertTrue(registersTwo.equals(registers));
        Assertions.assertTrue(registers.equals(registersTwo));
    }

    @Test
    void equalsValidFour() {
        Machine machineTwo = new Machine(new Registers());
        Registers registersTwo = machineTwo.getRegisters();
        registers.set(EAX, 3);
        registersTwo.set(ECX, 3);
        Assertions.assertFalse(registersTwo.equals(registers));
        Assertions.assertFalse(registers.equals(registersTwo));
    }

    @Test
    void toStringValid() {
        registers.set(EAX, 1);
        registers.set(EDI, 399);
        registers.set(EBP, 5);
        String expected = "[EAX = 1, EBX = 0, ECX = 0, EDX = 0, ESP = 0, " +
                "EBP = 5, ESI = 0, EDI = 399]";
        Assertions.assertEquals(expected, registers.toString());
    }
}
