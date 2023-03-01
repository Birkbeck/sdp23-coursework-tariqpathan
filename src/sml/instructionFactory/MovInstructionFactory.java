package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.helper.RegisterNameProvider;
import sml.instruction.AddInstruction;

public class MovInstructionFactory implements InstructionFactory {

    private final RegisterNameProvider registerNameProvider;


    public MovInstructionFactory(
            @Autowired RegisterNameProvider registerNameProvider) {
        this.registerNameProvider = registerNameProvider;
    }

    // get Spring to link the Factory to the Instruction
    @Override
    public AddInstruction create(String[] args) {
        String label = args[0];
        RegisterName result = registerNameProvider.getRegister(args[1]);
        RegisterName source = registerNameProvider.getRegister(args[2]);
        return new AddInstruction(label, result, source);
    }
}
