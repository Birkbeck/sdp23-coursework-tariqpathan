package sml.provider;

/**
 * Provides a way to convert the String to Integer.
 * Autowired into the InstructionFactory, set in ./resources/beans.xml
 *
 * @author Tariq Pathan
 */
public class IntProviderImpl implements IntProvider {

    @Override
    public int getInt(String value) {
        return Integer.parseInt(value);
    }
}
