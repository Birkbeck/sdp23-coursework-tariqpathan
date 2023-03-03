package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Performs a multiplication operation on two registers.
 * The integer value of the second (source) register is multiplied to the first (result).
 * Product is stored in the result register.
 * Values beyond Integer.MAX_VALUE or Integer.MIN_VALUE have undefined behaviour
 * and will not throw exceptions.
 *
 * @author Tariq Pathan
 */
public class MulInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "mul";

	/**
	 * Constructor: specifies the label (if it exists) and which registers to multiply
	 * @param label optional label (can be null)
	 * @param result the register to which result of the operation is stored
	 * @param source the register from which the value is used to multiply with
	 */
	public MulInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Carries out the execution of the instruction
	 * It is called by a machine instance (i.e. machine.execute())
	 * Note: Underflows and Overflows are not caught - large numbers have undefined behaviour
	 *
	 * @param m The instance of the machine where the instruction is executed
	 * @return on execution, a NORMAL_PROGRAM_COUNTER_UPDATE is returned
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 * value2);
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
		if (o instanceof MulInstruction other) {
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
