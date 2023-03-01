package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.helper.IntProvider;
import sml.helper.RegisterNameProvider;
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
    public MovInstruction create(String[] args) {
        String label = args[0];
        RegisterName result = registerNameProvider.getRegister(args[1]);
        int source = intProvider.getInt(args[2]);
        return new MovInstruction(label, result, source);
    }
}
