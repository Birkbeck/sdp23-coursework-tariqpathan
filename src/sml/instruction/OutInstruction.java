package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Prints out the value stored in a register.
 *
 * @author Tariq Pathan
 */
public class OutInstruction extends Instruction {
	private final RegisterName source;

	public static final String OP_CODE = "out";

	/**
	 * Constructor: specifies the label (if it exists), the register to store an int value to
	 * @param label optional label (can be null)
	 * @param source the register which stores the value
	 */
	public OutInstruction(String label, RegisterName source) {
		super(label, OP_CODE);
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
		int value = m.getRegisters().get(source);
		System.out.println(source.name() + ": " + value);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (o instanceof OutInstruction other) {
			return Objects.equals(this.label, other.label)
					&& Objects.equals(this.source, other.source);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, source);
	}
}
