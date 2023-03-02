package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.provider.IntProvider;
import sml.provider.RegisterNameProvider;
import sml.instruction.MovInstruction;

public class MovInstructionFactory implements InstructionFactory {

    private final RegisterNameProvider registerNameProvider;
    private final IntProvider intProvider;

    @Autowired
    public MovInstructionFactory(RegisterNameProvider registerNameProvider,
                                 IntProvider intProvider) {
        this.registerNameProvider = registerNameProvider;
        this.intProvider = intProvider;
    }

    // get Spring to link the Factory to the Instruction
    @Override
    public MovInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        int source = intProvider.getInt(args[1]);
        return new MovInstruction(label, result, source);
    }
}
