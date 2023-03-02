package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.RegisterName;
import sml.instruction.OutInstruction;
import sml.provider.RegisterNameProvider;

// TODO: write a JavaDoc

/**
 * @author Tariq Pathan
 */

public class OutInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;

    @Autowired
    public OutInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(1);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public OutInstruction create(String label, String[] args) {
        RegisterName source = registerNameProvider.getRegister(args[0]);
        return new OutInstruction(label, source);
    }
}
