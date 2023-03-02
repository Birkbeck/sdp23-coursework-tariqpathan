package sml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sml.instructionFactory.InstructionFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        String[] args = new String[2];
//        args[0] = scan();
//        args[1] = scan();
        //TODO: remove println
        System.out.println("Opcode: " + opcode);
        ArrayList<String> argsList = new ArrayList<>();
        while (!line.isEmpty()) {
            System.out.println("Line before: " + line);
            argsList.add(scan());
            System.out.println("Line after: " + line);
        }
        args = argsList.toArray(new String[0]);

        var factory = new ClassPathXmlApplicationContext("/beans.xml");
        InstructionFactory instructionFactory = (InstructionFactory) factory.getBean(opcode);
        Instruction i = instructionFactory.create(label, args);
        return i;
//        return null;
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

    public static void main(String[] args) {
        Translator t = new Translator("./test-resources/test4.sml");
        t.line = "EAX 3";
        System.out.println(t.scan());
        String s = "3";
        s = s.substring(0, 1);
        System.out.println(s);
    }
}