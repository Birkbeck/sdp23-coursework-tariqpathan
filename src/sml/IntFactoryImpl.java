package sml;

import org.springframework.beans.factory.annotation.Autowired;

public class IntFactoryImpl implements IntFactory {

    private final String value;

    @Autowired
    public IntFactoryImpl(String value) {
        this.value = value;
    }

    @Override
    public int getInt(String value) {
        return Integer.parseInt(value);
    }
}
