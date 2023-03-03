package sml.exception;

import sml.Instruction;

import java.util.List;

/**
 * Catches IllegalArgumentException when a duplicate label is used and rethrows with
 * additional context and information.
 *
 * @author Tariq Pathan
 */
public class DuplicateLabelException extends Exception {
    private final String message;

    /**
     * Constructor: label causing the error, the programs ArrayList and the index
     * @param label required, the duplicate label String
     * @param programs required, the ArrayList of instructions
     * @param index required, the index value of the existing instruction with label
     *              that already exists.
     */
    public DuplicateLabelException(String label,
                                   List<Instruction> programs, int index) {

        String instruction = programs.get(index).toString();
        this.message = "An error was generated when trying to add a label.\n"
                + "The label " + label + ", already exists at instruction:\n"
                + instruction + "\n"
                + "Program terminated with error";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
