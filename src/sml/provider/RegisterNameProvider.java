package sml.provider;

import sml.RegisterName;

public interface RegisterNameProvider {
    RegisterName getRegister(String name);
}
