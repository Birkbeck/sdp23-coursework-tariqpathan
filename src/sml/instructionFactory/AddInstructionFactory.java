package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.provider.RegisterNameProvider;
import sml.instruction.AddInstruction;

public class AddInstructionFactory implements InstructionFactory {

    private final RegisterNameProvider registerNameProvider;


    public AddInstructionFactory(
            @Autowired RegisterNameProvider registerNameProvider) {
        this.registerNameProvider = registerNameProvider;
    }

    // get Spring to link the Factory to the Instruction
    @Override
    public AddInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new AddInstruction(label, result, source);
    }
}
