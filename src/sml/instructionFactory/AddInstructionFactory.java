package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.AddInstruction;
import sml.provider.RegisterNameProvider;

/**
 * Creates an AddInstruction, with the RegisterNames added via the RegisterNameProvider
 * which is injected and converts a String to an appropriate RegisterName
 *
 * @author Tariq Pathan
 */
public class AddInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;
    private static final int ARGS_REQUIRED = 2;

    /**
     * Constructor: Providers that help to convert the Strings to appropriate types
     * @param registerNameProvider set in the /resources/beans.xml file
     */
    @Autowired
    public AddInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(ARGS_REQUIRED);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public AddInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new AddInstruction(label, result, source);
    }
}
