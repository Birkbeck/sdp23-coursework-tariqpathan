package sml.provider;

import sml.RegisterName;
import sml.Registers;

/**
 * Provides a way to convert the String to RegisterName.
 * Autowired into the InstructionFactory, set in ./resources/beans.xml
 *
 * @author Tariq Pathan
 */
public class RegisterNameProviderImpl implements RegisterNameProvider {

    @Override
    public RegisterName getRegister(String name) {
        return Registers.Register.valueOf(name);
    }
}
