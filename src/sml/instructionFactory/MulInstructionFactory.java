package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.instruction.SubInstruction;
import sml.provider.RegisterNameProvider;

public class SubInstructionFactory implements InstructionFactory {

    private final RegisterNameProvider registerNameProvider;

    @Autowired
    public SubInstructionFactory(RegisterNameProvider registerNameProvider) {
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public SubInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new SubInstruction(label, result, source);
    }
}
