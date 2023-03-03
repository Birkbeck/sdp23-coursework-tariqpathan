package sml;

/**
 * Represents an abstract instruction for Instruction subclasses.
 * Subclasses must be named OpcodeInstruction, where Opcode is a 3-character opcode.
 *
 * @author Tariq Pathan
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name, must be lowercase and 3 characters and must match
	 *               instruction. e.g. opcode "abc" for AbcInstruction
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	/**
	 * Returns the label for an instruction, if it exists
	 *
	 * @return empty String if a label is null or the label name followed by a colon
	 */

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean?
	// abstract here means that a non-abstract (concrete) subclass that extends Instruction.java
	// must implement the method.
	// If the subclass extending instruction.java does not implement the method, that subclass
	// must also be declared as abstract.

	/**
	 * Provides a String representation of the Instruction in the format:
	 * label: opcode operand1 operandN (label can be optional)
	 *
	 * @return String value of the instruction
	 */
	@Override
	public abstract String toString();

	/**
	 * Compares object to Instruction
	 *
	 * @param obj object to compare to
	 * @return true if obj is an instruction and label, opcode and operands match
	 * else returns false
	 *
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * For equals and hashcode contract. Makes sure that hashcode for Instructions
	 * that have the same label, opcode and operands are the same.
	 *
	 * @return integer
	 */
	@Override
	public abstract int hashCode();

}
