package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.instruction.AddInstruction;
import sml.provider.RegisterNameProvider;

public class AddInstructionFactory implements InstructionFactory {

    private final RegisterNameProvider registerNameProvider;

    @Autowired
    public AddInstructionFactory(RegisterNameProvider registerNameProvider) {
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public AddInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new AddInstruction(label, result, source);
    }
}
