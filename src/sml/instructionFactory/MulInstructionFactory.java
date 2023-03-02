package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.instruction.MulInstruction;
import sml.provider.RegisterNameProvider;

public class MulInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;

    @Autowired
    public MulInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(2);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public MulInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new MulInstruction(label, result, source);
    }
}
