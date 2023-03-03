package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the registers for a machine, which are simple storage areas used in computer memory.
 * A Register can only store integers.
 * <p>
 * The registers class is used to initialise, clear, and set the values of registers to a
 * specific value and is called by an instance of a machine.
 * <p>
 * Each machine requires an instance of Registers.
 * Upon initialisation, all registers have a value of 0.
 * <p>
 * Note: As registers store Integers, arithmetic operations can cause overflow or underflow errors.
 * This is not caught and can result in undefined behaviour.
 *
 * @author Tariq Pathan
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();

    public Registers() {
        clear(); // the class is final
    }

    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value    new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register) register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register) register);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers other) {
            return registers.equals(other.registers);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI
    }
}
