package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.instruction.SubInstruction;
import sml.provider.RegisterNameProvider;

public class SubInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;

    @Autowired
    public SubInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(2);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public SubInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new SubInstruction(label, result, source);
    }
}
