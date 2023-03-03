package sml.exception;

import sml.Instruction;

import java.util.List;

public class DuplicateLabelException extends Exception {
    private final String message;

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
