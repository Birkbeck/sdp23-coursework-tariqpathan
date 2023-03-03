package sml;

import sml.exception.DuplicateLabelException;
import sml.exception.InvalidInstructionException;
import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class translates an sml file into a list of instructions that are called
 * when a machines execute method is called.
 * This class is responsible for converting the instructions in String format to
 * an Instruction format that can be used with a machine instance.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Tariq Pathan
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

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
        } catch (NullPointerException exc) {
            throw new InvalidInstructionException(exc, null, null, line);
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
        try{
            switch (opcode) {
                case AddInstruction.OP_CODE -> {
                    String r = scan();
                    String s = scan();
                    return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
                }
                case DivInstruction.OP_CODE -> {
                    String r = scan();
                    String s = scan();
                    return new DivInstruction(label, Register.valueOf(r), Registers.Register.valueOf(s));
                }
                case JnzInstruction.OP_CODE -> {
                    String s = scan();
                    String L = scan();
                    return new JnzInstruction(label, Register.valueOf(s), L);
                }
                case MovInstruction.OP_CODE -> {
                    String r = scan();
                    String x = scan();
                    try {
                        int source = Integer.parseInt(x);
                        return new MovInstruction(label, Register.valueOf(r), source);
                    } catch (NumberFormatException exc) {
                        throw new InvalidInstructionException(exc, label, opcode, line);
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

                default -> {
                    throw new InvalidInstructionException(
                            new IllegalArgumentException("Unknown instruction"), label, opcode, line);
                }
            }
        } catch (IllegalArgumentException exc) {
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

    public static void main(String[] args) throws InvalidInstructionException {
        Translator t = new Translator("xyz");
        t.line = "add EAX";
        Instruction i = t.getInstruction(null);
    }
}