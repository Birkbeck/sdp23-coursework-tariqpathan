package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.AddInstruction;
import sml.provider.RegisterNameProvider;

public class AddInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;
    private static final int ARGS_REQUIRED = 2;

    @Autowired
    public AddInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(ARGS_REQUIRED);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public AddInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new AddInstruction(label, result, source);
    }
}
