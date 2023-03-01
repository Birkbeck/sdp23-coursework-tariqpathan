package sml;

import org.springframework.beans.factory.annotation.Autowired;

public class RegisterFactoryImpl implements RegisterFactory {

    private final String name;

    @Autowired
    public RegisterFactoryImpl(String name) {
        this.name = name;
    }

    @Override
    public RegisterName getRegister(String name) {
        return Registers.Register.valueOf(name);
    }
}
