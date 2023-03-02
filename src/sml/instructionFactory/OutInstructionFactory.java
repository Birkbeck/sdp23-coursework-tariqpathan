package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.OutInstruction;
import sml.provider.RegisterNameProvider;

// TODO: write a JavaDoc

/**
 * @author Tariq Pathan
 */

public class OutInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;
    private static final int ARGS_REQUIRED = 1;


    @Autowired
    public OutInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(ARGS_REQUIRED);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public OutInstruction create(String label, String[] args) {
        RegisterName source = registerNameProvider.getRegister(args[0]);
        return new OutInstruction(label, source);
    }
}
