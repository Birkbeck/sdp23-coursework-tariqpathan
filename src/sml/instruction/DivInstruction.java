package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Performs a division operation on two registers.
 * The integer value of the first (result) register is divided by the second register (source).
 * This quotient is stored in the result register.
 *
 * @author Tariq Pathan
 */
public class DivInstruction extends Instruction {
    public static final String OP_CODE = "div";
    private final RegisterName result;
    private final RegisterName source;

    /**
     * Constructor: specifies the label (if it exists) and dividend, divisor registers
     *
     * @param label  optional label (can be null)
     * @param result the register to which result of the operation is stored and also the dividend
     * @param source the register where the divisor value is stored
     */
    public DivInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    /**
     * Carries out the execution of the instruction
     * It is called by a machine instance (i.e. machine.execute())
     *
     * @param m The instance of the machine where the instruction is executed
     * @return on successful execution, a NORMAL_PROGRAM_COUNTER_UPDATE is returned
     * @throws ArithmeticException if source register is 0 and division is attempted.
     */
    @Override
    public int execute(Machine m) throws IllegalArgumentException {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        try {
            m.getRegisters().set(result, value1 / value2);
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        } catch (ArithmeticException exc) {
            throw new ArithmeticException("/ by zero. " + "Register: "
                    + this.source.toString() + " has a value of 0\n"
                    + "Caused by instruction: " + this);
        }
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof DivInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.source, other.source);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, result, source);
    }
}
