package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadAndCreateInstructionTest {
    private Machine machine;
    private final String filePath = "./test-resources/instructionFiles/";
    private final String extension = ".sml";

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
    }

    @AfterEach
    void tearDown() {
        machine = null;
    }

    @Test
    void createAddInstructionValid() throws Exception {
        String opcode = "add";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(0);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("add EAX EBX", instruction.toString());
    }

    @Test
    void createAddInstructionWithLabelValid() throws Exception {
        String opcode = "add";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(1);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("f1: add EAX EBX", instruction.toString());
    }

    @Test
    void createDivInstructionValid() throws Exception {
        String opcode = "div";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(0);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("div EAX EBX", instruction.toString());
    }

    @Test
    void createDivInstructionWithLabelValid() throws Exception {
        String opcode = "div";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(1);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("f1: div EAX EBX", instruction.toString());
    }

    @Test
    void createJnzInstructionValid() throws Exception {
        String opcode = "jnz";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(0);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("jnz ECX f1", instruction.toString());
    }

    @Test
    void createJnzInstructionWithLabelValid() throws Exception {
        String opcode = "jnz";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(1);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("f2: jnz ECX f1", instruction.toString());
    }

    @Test
    void createMovInstructionValid() throws Exception {
        String opcode = "mov";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(0);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("mov EAX 4", instruction.toString());
    }

    @Test
    void createMovInstructionWithLabelValid() throws Exception {
        String opcode = "mov";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(1);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("f1: mov EAX 4", instruction.toString());
    }

    @Test
    void createMulInstructionValid() throws Exception {
        String opcode = "mul";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(0);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("mul EAX EBX", instruction.toString());
    }

    @Test
    void createMulInstructionWithLabelValid() throws Exception {
        String opcode = "mul";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(1);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("f1: mul EAX EBX", instruction.toString());
    }

    @Test
    void createOutInstructionValid() throws Exception {
        String opcode = "out";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(0);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("out EAX", instruction.toString());
    }

    @Test
    void createOutInstructionWithLabelValid() throws Exception {
        String opcode = "out";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(1);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("f1: out EAX", instruction.toString());
    }

    @Test
    void createSubInstructionValid() throws Exception {
        String opcode = "sub";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(0);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("sub EAX EBX", instruction.toString());
    }

    @Test
    void createSubInstructionWithLabelValid() throws Exception {
        String opcode = "sub";
        String fileName = filePath + opcode + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Instruction instruction = machine.getProgram().get(1);
        Assertions.assertEquals(opcode, instruction.getOpcode());
        Assertions.assertEquals("f1: sub EAX EBX", instruction.toString());
    }

}
