package sml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sml.instructionFactory.InstructionFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        /*//TODO: remove println
        System.out.println("Opcode: " + opcode);
        // where the Instruction.OP_CODE matches the string opcode
        //
        // create that class
        // load the params
        String instructionClassName = "sml.instruction." +
                opcode.substring(0, 1).toUpperCase() + opcode.substring(1) +
                "Instruction";


        String lineBefore = line;
        try {
            Class<?> instruction = Class.forName(instructionClassName);
            if (instruction.getSuperclass() != Instruction.class) {
                throw new IllegalArgumentException("Invalid instruction");
            }

            Constructor<?>[] constructors = instruction.getDeclaredConstructors();
            Constructor<?> constructor = constructors[0];
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            // Get args required for constructor
            int paramCount = constructor.getParameterCount();
            String[] stringArgs = new String[paramCount];
            stringArgs[0] = label;
            IntStream.range(1, paramCount).forEach(i -> stringArgs[i] = scan());
            //TODO: remove println
            System.out.println(Arrays.asList(stringArgs).stream().collect(Collectors.joining(", ")));
            // stringArgs is an array of all the arguments as strings for the chosen constructor
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
                    constructorArgs[i] = Register.valueOf(stringArgs[i]);
                }
                constructorArgs[i] = null;
            });
            return (Instruction) constructor.newInstance(constructorArgs);

        } catch (ClassNotFoundException exc) {
            System.out.println(opcode + " instruction was not found -> " + instructionClassName);
        } catch (IllegalArgumentException exc) {
            System.out.println(exc.getMessage());
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException exc) {

        }
        line = lineBefore;*/

        var factory = new ClassPathXmlApplicationContext("/beans.xml");
        InstructionFactory instructionFactory = (InstructionFactory) factory.getBean("add");
        String[] args2 = {"label", "EAX", "EBX"};
        Instruction i = instructionFactory.create(args2);
        System.out.println(i.toString());
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