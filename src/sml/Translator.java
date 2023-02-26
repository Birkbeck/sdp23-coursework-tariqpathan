package sml;

import sml.instruction.*;

import javax.lang.model.type.PrimitiveType;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    private String folderPath = "./instruction/";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        //TODO: remove
        System.out.println("Opcode: " + opcode);
        // where the Instruction.OP_CODE matches the string opcode
        //
        // create that class
        // load the params
        String instructionClassName = "sml.instruction." +
                opcode.substring(0, 1).toUpperCase() + opcode.substring(1) +
                "Instruction";

        try {
            Class<?> instruction = Class.forName(instructionClassName);
            if (instruction.getSuperclass() != Instruction.class) {
                throw new IllegalArgumentException("Invalid instruction");
            }

            Constructor<?>[] constructors = instruction.getDeclaredConstructors();
            Constructor<?> constructor = constructors[0];
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            //Arrays.stream(parameterTypes).forEach(System.out::println);
            Object[] constructorArgs = new Object[constructor.getParameterCount()];
            constructorArgs[0] = label;
            Arrays.asList(parameterTypes).forEach(System.out::println);
            for (int i = 1; i < constructor.getParameterCount(); i++) {

            //                if (parameterTypes[i])
//                constructorArgs[i];
            }


        } catch (ClassNotFoundException exc) {
            System.out.println(opcode + " instruction was not found -> " + instructionClassName);
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
        }

        //TODO: Remove switch statement
        switch (opcode) {
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new AddInstruction(label, getRegister(r), getRegister(s));
            }
            case DivInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case JnzInstruction.OP_CODE -> {
                String s = scan();
                String L = scan();
                return new JnzInstruction(label, Register.valueOf(s), L);
            }
            case MovInstruction.OP_CODE -> {
                String r = scan();
                String x = scan();
                // TODO: Check if this try-catch is in appropriate place
                try {
                    int source = Integer.parseInt(x);
                    return new MovInstruction(label, Register.valueOf(r), source);
                } catch (NumberFormatException exc) {
                    System.out.println(MovInstruction.OP_CODE + " " + r + " " + x
                            + ": " + x + " could not be converted to integer");
                }
            }
            case MulInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case OutInstruction.OP_CODE -> {
                String s = scan();
                return new OutInstruction(label, Register.valueOf(s));
            }
            case SubInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            // TODO: add code for all other types of instructions: Attempted

            // TODO: Then, replace the switch by using the Reflection API

            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)

            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }
        return null;
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    private Register getRegister(String s) {
        try {
            return Register.valueOf(s);
        } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException("Register with an address of " + s + " does not exist");
        }
    }

//    private static Class<?> toWrapper(Class<?> testClass) {
//        return PRIMITIVE_TYPE_WRAPPERS.getOrDefault(testClass, testClass);
//    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
    // TODO: Remove
    public static void main(String[] args) {
        Translator t = new Translator("anyname");
        t.line = "mov ECX EFX";
        Instruction i1 = t.getInstruction(null);
        System.out.println(i1.toString());
    }
}