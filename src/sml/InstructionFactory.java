package sml;

import java.lang.reflect.Constructor;
import java.util.stream.IntStream;

public class InstructionFactory {

    private static final InstructionFactory instance = new InstructionFactory();
    private Instruction instructionProvider;

    public static InstructionFactory getInstance() {
        return instance;
    }

    public Instruction getInstructionProvider() {
        return instructionProvider;
    }
    private InstructionFactory() {

        //TODO: get appropriate implementation class: how?

    }
    private Instruction newInstanceOf(String className) {
        //TODO: create the constructor
        //  return the appropriate instruction
        return null;
    }

    private void constructorArgs() {
        // need opcode from translator and string
        //
        // create that class
        // load the params
        String instructionClassName = "sml.instruction." +
                opcode.substring(0, 1).toUpperCase() + opcode.substring(1) +
                "Instruction";

        Class<?> instruction = Class.forName(instructionClassName);
        if (instruction.getSuperclass() != Instruction.class) {
            throw new IllegalArgumentException("Invalid instruction");
        }

        Constructor<?>[] constructors = instruction.getDeclaredConstructors();
        Constructor<?> constructor = constructors[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        // Get args required for constructor
        int paramCount = constructor.getParameterCount();

        // now we will convert the strings to appropriate types for the constructor
        Object[] constructorArgs = new Object[paramCount];

        IntStream.range(0, paramCount).forEach(i -> {
            if (parameterTypes[i] == String.class) {
                constructorArgs[i] = stringArgs[i];
            }
            if (parameterTypes[i] == int.class) {
                constructorArgs[i] = Integer.parseInt(stringArgs[i]);
            }
            if (parameterTypes[i] == RegisterName.class) {
                constructorArgs[i] = Registers.Register.valueOf(stringArgs[i]);
            }
            constructorArgs[i] = null;
        });
        return (Instruction) constructor.newInstance(constructorArgs);
    }

}
