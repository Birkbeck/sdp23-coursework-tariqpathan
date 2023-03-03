package sml.exception;

public class InvalidInstructionException extends Exception {
    private final String message;

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
