package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.instruction.DivInstruction;
import sml.provider.RegisterNameProvider;

public class DivInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;

    @Autowired
    public DivInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(2);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public DivInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new DivInstruction(label, result, source);
    }
}
