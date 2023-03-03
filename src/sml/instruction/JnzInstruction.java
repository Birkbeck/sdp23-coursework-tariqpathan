package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Performs a jump operation to specified label if the source register value is not 0.
 * <p>
 * Labels can only be checked after all instructions are added.
 * This is because a label allows a jump 'forward' to an instruction not yet added.
 * Labels are checked for validity when execute is called.
 *
 * @author Tariq Pathan
 */
public class JnzInstruction extends Instruction {
    public static final String OP_CODE = "jnz";
    private final RegisterName source;
    private final String goToLabel;

    /**
     * Constructor: specifies the label (if it exists), the register value to be checked
     * and the label for the instruction to jump to.
     *
     * @param label     optional label (can be null)
     * @param source    the register where the value is checked
     * @param goToLabel the String value of the label to jump to
     */
    public JnzInstruction(String label, RegisterName source, String goToLabel) {
        super(label, OP_CODE);
        this.source = source;
        this.goToLabel = goToLabel;
    }

    /**
     * Carries out the execution of the instruction
     * It is called by a machine instance (i.e. machine.execute())
     *
     * @param m The instance of the machine where the instruction is executed
     * @return int NORMAL_PROGRAM_COUNTER_UPDATE if the source value is 0
     * else, the program counter value for a valid label is returned
     * @throws NullPointerException if a valid address is not found for a label.
     */
    @Override
    public int execute(Machine m) throws NullPointerException {
        // throws an exception if label invalid, even if not jumping
        try {
            int goToAddress = m.getLabels().getAddress(goToLabel);
            int value = m.getRegisters().get(source);
            if (value == 0) return NORMAL_PROGRAM_COUNTER_UPDATE;
            return goToAddress;
        } catch (NullPointerException exc) {
            throw new NullPointerException("The label: " + goToLabel + " does not exist\n"
                    + "Caused by instruction: " + this);
        }

    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + goToLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof JnzInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.source, other.source)
                    && Objects.equals(this.goToLabel, other.goToLabel);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, source, goToLabel);
    }
}
