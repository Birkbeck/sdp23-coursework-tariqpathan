package sml.instructionFactory;

import sml.Instruction;

public abstract class InstructionFactory {

    private final int ARGS_REQUIRED;

    public InstructionFactory(int argsRequired) {
        this.ARGS_REQUIRED = argsRequired;
    }

    public abstract Instruction create(String label, String[] args);

    public boolean checkArgLength(String[] args) {
        return (this.ARGS_REQUIRED == args.length);
    }
}
