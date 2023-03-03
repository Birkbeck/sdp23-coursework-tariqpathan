package sml;

/**
 * Represents an abstract instruction factory for InstructionFactory subclasses.
 * Subclasses must have the same name as the instruction they create, followed by Factory.
 * e.g "XyzInstruction" is made by "XyzInstructionFactory"
 * This also requires a bean with the id as "xyz" in the "/resources/beans.xml" file.
 * The opcode for XyzInstruction would also be xyz and must be unique.
 *
 * argRequired method was included as this number would be decided by the create method,
 * which the factory is responsible for.
 *
 * @author Tariq Pathan
 */
public abstract class InstructionFactory {

    private final int argsRequired;

    /**
     * Constructor: number of Strings in the args array that are required for the create method.
     * This number does not include the label.
     * @param argsRequired int required for length of args array
     */
    public InstructionFactory(int argsRequired) {
        this.argsRequired = argsRequired;
    }

    /**
     *
     * @param label (can be null)
     * @param args Array of strings that will be converted to the appropriate type by the factory
     *             create method
     * @return Instruction
     */
    public abstract Instruction create(String label, String[] args);

    public int getArgLengthRequired() {
        return this.argsRequired;
    }
}
