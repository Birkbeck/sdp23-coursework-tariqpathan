package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.JnzInstruction;
import sml.provider.RegisterNameProvider;

// TODO: write a JavaDoc

/**
 * @author Tariq Pathan
 */

public class JnzInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;
    private static final int ARGS_REQUIRED = 2;

    @Autowired
    public JnzInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(ARGS_REQUIRED);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public JnzInstruction create(String label, String[] args) {
        RegisterName source = registerNameProvider.getRegister(args[0]);
        String goToLabel = args[1];
        return new JnzInstruction(label, source, goToLabel);
    }
}
