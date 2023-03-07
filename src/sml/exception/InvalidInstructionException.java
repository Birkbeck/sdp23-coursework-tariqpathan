package sml.exception;

/**
 * Catches Various Exceptions thrown during the initialisation of a machine's program
 * Re-throws with additional context and information
 *
 * Provides user the string of the instruction causing the error and the error message throwing
 * the original exception.
 *
 * @author Tariq Pathan
 */
public class InvalidInstructionException extends Exception {
    private final String message;

    /**
     * Constructor: label causing the error, the programs ArrayList and the index
     * @param cause original exception that threw the error
     * @param label label for the instruction generating the error (null if no label)
     * @param opcode required, opcode of instruction
     * @param line rest of line from the parsed instruction that is causing the error.
     */
    public InvalidInstructionException(Throwable cause,
                                       String label, String opcode, String line) {
        super(cause);
        String instruction = (label != null) ? label : "" + opcode + " " + line.trim();
        this.message = "An error was generated when trying to create an instruction.\n"
                + "The following instruction: " + instruction + "\n"
                + "caused: " + cause + "\n"
                + "Program terminated with error.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
