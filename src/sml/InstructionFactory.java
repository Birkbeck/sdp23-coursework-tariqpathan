package sml;

public abstract class InstructionFactory {

    private final int argsRequired;

    public InstructionFactory(int argsRequired) {
        this.argsRequired = argsRequired;
    }

    public abstract Instruction create(String label, String[] args);

    public boolean checkArgLength(String[] args) {
        return (this.argsRequired == args.length);
    }
}
