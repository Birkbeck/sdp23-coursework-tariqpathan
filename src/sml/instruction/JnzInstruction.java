package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
	private final RegisterName source;
	private final Label jumpToLabel ;

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName source, Label jumpToLabel) {
		super(label, OP_CODE);
		this.source = source;
		this.jumpToLabel = jumpToLabel;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(source);
		if (value1 == 0) return NORMAL_PROGRAM_COUNTER_UPDATE;
		return -2;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source + " " + jumpToLabel.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		// fully compares fields
		if (o instanceof JnzInstruction other) {
			return Objects.equals(this.label, other.label)
					&& Objects.equals(this.source, other.source)
					&& Objects.equals(this.jumpToLabel, other.jumpToLabel);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, source, jumpToLabel);
	}
}
