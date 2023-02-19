package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class OutInstruction extends Instruction {
	private final RegisterName source;

	public static final String OP_CODE = "out";

	public OutInstruction(String label, RegisterName source) {
		super(label, OP_CODE);
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(source);
		System.out.println(source.name() + ": " + value1);
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
		// fully compares fields
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
