package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.JnzInstruction;
import sml.provider.RegisterNameProvider;

/**
 * Creates a JnzInstruction, with the RegisterNames added via the RegisterNameProvider
 * which is injected and converts a String to an appropriate RegisterName
 *
 * @author Tariq Pathan
 */
public class JnzInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;
    private static final int ARGS_REQUIRED = 2;

    /**
     * Constructor: Providers that help to convert the Strings to appropriate types
     * @param registerNameProvider set in the /resources/beans.xml file
     */
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
