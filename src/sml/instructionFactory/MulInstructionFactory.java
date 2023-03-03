package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.MulInstruction;
import sml.provider.RegisterNameProvider;

/**
 * Creates a MulInstruction, with the RegisterNames added via the RegisterNameProvider
 * which is injected and converts a String to an appropriate RegisterName
 *
 * @author Tariq Pathan
 */
public class MulInstructionFactory extends InstructionFactory {

    private static final int ARGS_REQUIRED = 2;
    private final RegisterNameProvider registerNameProvider;

    /**
     * Constructor: Providers that help to convert the Strings to appropriate types
     *
     * @param registerNameProvider set in the /resources/beans.xml file
     */
    @Autowired
    public MulInstructionFactory(RegisterNameProvider registerNameProvider) {
        super(ARGS_REQUIRED);
        this.registerNameProvider = registerNameProvider;
    }

    @Override
    public MulInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        RegisterName source = registerNameProvider.getRegister(args[1]);
        return new MulInstruction(label, result, source);
    }
}
