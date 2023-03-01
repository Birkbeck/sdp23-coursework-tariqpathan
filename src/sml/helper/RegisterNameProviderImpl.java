package sml.helper;

import org.springframework.stereotype.Component;
import sml.RegisterName;
import sml.Registers;

@Component
public class RegisterNameProviderImpl implements RegisterNameProvider {

    @Override
    public RegisterName getRegister(String name) {
        return Registers.Register.valueOf(name);
    }
}
