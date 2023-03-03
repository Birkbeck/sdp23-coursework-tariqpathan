package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Performs a move operation on one register.
 * Stores an int value (source) to a register (result)
 *
 * @author Tariq Pathan
 */
public class MovInstruction extends Instruction {
    public static final String OP_CODE = "mov";
    private final RegisterName result;
    private final int source;

    /**
     * Constructor: specifies the label (if it exists), the register to store an int value to
     *
     * @param label  optional label (can be null)
     * @param result the register to which result of the operation is stored
     * @param source int value that will be stored to the register
     */
    public MovInstruction(String label, RegisterName result, int source) {
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
     */
    @Override
    public int execute(Machine m) {
        int value = this.source;
        m.getRegisters().set(result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof MovInstruction other) {
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
