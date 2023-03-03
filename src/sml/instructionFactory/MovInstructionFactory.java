package sml.instructionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import sml.InstructionFactory;
import sml.RegisterName;
import sml.instruction.MovInstruction;
import sml.provider.IntProvider;
import sml.provider.RegisterNameProvider;

/**
 * Creates a MovInstruction, with the RegisterNames added via the RegisterNameProvider
 * which is injected and converts a String to an appropriate RegisterName
 * Int values converted via an IntProvider
 *
 * @author Tariq Pathan
 */
public class MovInstructionFactory extends InstructionFactory {

    private final RegisterNameProvider registerNameProvider;
    private final IntProvider intProvider;

    /**
     * Constructor: Providers that help to convert the Strings to appropriate types
     *
     * @param registerNameProvider set in the /resources/beans.xml file
     * @param intProvider          set in the /resources/beans.xml file
     */
    @Autowired
    public MovInstructionFactory(RegisterNameProvider registerNameProvider,
                                 IntProvider intProvider) {
        super(2);
        this.registerNameProvider = registerNameProvider;
        this.intProvider = intProvider;
    }

    @Override
    public MovInstruction create(String label, String[] args) {
        RegisterName result = registerNameProvider.getRegister(args[0]);
        int source = intProvider.getInt(args[1]);
        return new MovInstruction(label, result, source);
    }
}
