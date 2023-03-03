package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Performs an addition operation on two registers.
 * The integer value of the second (source) register is added to the first (result).
 * Will not throw exceptions if values go beyond Integer.MAX_VALUE and Integer.MIN_VALUE
 *
 * @author Tariq Pathan
 */
public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "add";

	/**
	 * Constructor: specifies the label (if it exists) and which registers to add
	 * @param label optional label (can be null)
	 * @param result the register to which result of the operation is stored
	 * @param source the register from which the value is used
	 */
	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Carries out the execution of the instruction
	 * It is called by a machine instance (i.e. machine.execute())
	 * Note: Underflows and Overflows are not caught - the integer value will wrap around
	 *
	 * @param m The instance of the machine where the instruction is executed
	 * @return on execution, a NORMAL_PROGRAM_COUNTER_UPDATE is returned
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
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
		if (o instanceof AddInstruction other) {
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
