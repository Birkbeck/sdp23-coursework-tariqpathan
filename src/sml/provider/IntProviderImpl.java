package sml.provider;

public class IntProviderImpl implements IntProvider{

    @Override
    public int getInt(String value) {
        return Integer.parseInt(value);
    }
}
