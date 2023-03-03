package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Represents an instruction factory for creating Instructions using reflection API.
 * Appropriate class names for instructions are found via opcode.
 * The opcode for XyzInstruction would also be xyz and must be unique.
 * Use of static means that in modern JVMs, this implementation is thread-safe
 * (instead of lazy loading).
 *
 * @author Tariq Pathan
 */
public class InstructionFactory {

    private static final InstructionFactory INSTANCE = new InstructionFactory();

    private InstructionFactory() {}

    /**
     * Gets the INSTANCE of the instruction factory, of which there should only be one.
     */
    public static InstructionFactory getInstance() {
        return INSTANCE;
    }

    private String createClassName(String opcode) {
        String packageAddress = "sml.instruction.";
        String capitalisedOpcode = opcode.substring(0, 1).toUpperCase() + opcode.substring(1);
        String superClassName = "Instruction";
        return packageAddress + capitalisedOpcode + superClassName;
    }

    private Constructor getConstructor(String instructionClassName) throws
            ClassNotFoundException, NoSuchMethodException{

        Class<?> instruction = Class.forName(instructionClassName);
        if (instruction.getSuperclass() != Instruction.class) {
            throw new IllegalArgumentException("Invalid opcode");
        }
        Constructor[] constructors = instruction.getDeclaredConstructors();
        return constructors[0];
    }

    private Object[] getConstructorArgs(Constructor constructor, String label, String[] stringArgs)
    throws IllegalArgumentException{
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int paramCount = constructor.getParameterCount();
        if (paramCount - 1 != stringArgs.length)
            throw new IllegalArgumentException(paramCount - 1 + " arguments required, "
                    + stringArgs.length + " provided after opcode");

        // create an arraylist with all the constructor arguments in string format
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(stringArgs));
        argsList.add(0, label);
        System.out.println(argsList);;

        // array of arguments to be passed to constructor
        Object[] constructorArgs = new Object[paramCount];

        // convert the strings to appropriate types for the constructor
        IntStream.range(0, paramCount).forEach(i -> {
            if (parameterTypes[i] == String.class) {
                constructorArgs[i] = argsList.get(i);
            }
            else if (parameterTypes[i] == int.class) {
                constructorArgs[i] = Integer.parseInt(argsList.get(i));
            }
            else if (parameterTypes[i] == RegisterName.class) {
                constructorArgs[i] = Registers.Register.valueOf(argsList.get(i));
            }
            else constructorArgs[i] = null;
        });
        return constructorArgs;
    }

    /**
     *
     * @param opcode required String for operation
     * @param label (optional) String for label
     * @param args An array of Strings to be converted, must match the instruction constructors parameter count - 1
     * @return an instruction of the opcode type
     * @throws ClassNotFoundException if the instruction class name is not found
     * @throws NoSuchMethodException if no constructor exists for instruction class
     * @throws InvocationTargetException when constructor is called
     * @throws InstantiationException when the instruction cannot be instantiated
     * @throws IllegalAccessException if trying to set fields that are not accessible
     * @throws IllegalArgumentException if the args array doesn't match parameterCount - 1 and if the Strings in
     *      * the args array cannot be converted to the required type
     */
    public Instruction create(String opcode, String label, String[] args) throws
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, IllegalArgumentException {

        String className = createClassName(opcode);
        Constructor constructor = getConstructor(className);
        Object[] constructorArgs = getConstructorArgs(constructor, label, args);
        return (Instruction) constructor.newInstance(constructorArgs);
    }
}

