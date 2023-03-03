package sml.provider;

import sml.RegisterName;
import sml.Registers;

public class RegisterNameProviderImpl implements RegisterNameProvider {

    @Override
    public RegisterName getRegister(String name) {
        return Registers.Register.valueOf(name);
    }
}
