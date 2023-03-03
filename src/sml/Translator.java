package sml;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sml.exception.DuplicateLabelException;
import sml.exception.InvalidInstructionException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException,
            InvalidInstructionException, DuplicateLabelException {
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
        } catch (IllegalArgumentException exc) {
            int index = labels.getAddress(exc.getMessage());
            throw new DuplicateLabelException(exc.getMessage(), program, index);
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
    private Instruction getInstruction(String label) throws InvalidInstructionException {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        var factory = new ClassPathXmlApplicationContext("/beans.xml");
        try {
            InstructionFactory instructionFactory = (InstructionFactory) factory.getBean(opcode);
            String[] args = line.trim().split("\\s+");
            if (!instructionFactory.checkArgLength(args)) {
                throw new IllegalArgumentException("Invalid number of arguments for instruction type");
            }
            Instruction instruction = instructionFactory.create(label, args);
            return instruction;
        } catch (NoSuchBeanDefinitionException | IllegalArgumentException exc) {
            throw new InvalidInstructionException(exc, label, opcode, line);
        }
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

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

    //TODO: remove main method
    public static void main(String[] args) {
        Translator t = new Translator("abc");
        String[] errors = {
                "abc EAX EAX", "add ZZZ EAX", "add EAX EAX EAX", "sub 4 EAX", "mul EAX 5",
                "div ECX", "jnz ECX f100", "mov EAX abd"};
        Arrays.asList(errors).forEach(e -> {
            System.out.println(e);
            try {
                t.line = e;
                t.getInstruction(null);
            } catch (InvalidInstructionException exc) {
                System.out.println(exc.getMessage());
            }
        });
    }
}