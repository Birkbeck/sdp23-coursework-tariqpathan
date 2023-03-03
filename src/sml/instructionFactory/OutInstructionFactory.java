package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.OutInstruction;
import sml.provider.RegisterNameProvider;

/**
 * Creates an OutInstruction, with the RegisterNames added via the RegisterNameProvider
 * which is injected and converts a String to an appropriate RegisterName
 *
 * @author Tariq Pathan
 */
public class OutInstructionFactory extends InstructionFactory {

    private static final int ARGS_REQUIRED = 1;
    private final RegisterNameProvider registerNameProvider;

    /**
     * Constructor: Providers that help to convert the Strings to appropriate types
     *
     * @param registerNameProvider set in the /resources/beans.xml file
     */
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
