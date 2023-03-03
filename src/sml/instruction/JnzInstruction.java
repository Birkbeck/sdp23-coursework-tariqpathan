package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 * Labels can only be checked after all instructions are added.
 * This is because a label allows a jump 'forward' to an instruction not yet added.
 * Here, labels are only checked when execute is called.
 */

public class JnzInstruction extends Instruction {
	private final RegisterName source;
	private final String goToLabel;

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName source, String goToLabel) {
		super(label, OP_CODE);
		this.source = source;
		this.goToLabel = goToLabel;
	}

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
					+ "Caused by instruction: " + this.toString());
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
		// fully compares fields
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
