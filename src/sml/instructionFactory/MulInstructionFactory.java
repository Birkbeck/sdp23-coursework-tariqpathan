package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.MulInstruction;
import sml.provider.RegisterNameProvider;

public class MulInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;
    private static final int ARGS_REQUIRED = 2;

    @Autowired
    public MulInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(ARGS_REQUIRED);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public MulInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new MulInstruction(label, result, source);
    }
}
