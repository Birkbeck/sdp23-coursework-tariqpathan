package sml.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Registers;
import sml.Translator;

class ReadAndTranslateExceptionsTest {
    private Machine machine;
    private final String filePath = "./test-resources/throwsExceptionReadAndTranslate/";
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
    void invalidOpcodeThrows() {
        String errorType = "invalidOpcode";
        String fileName = filePath + errorType + extension;
        Translator translator = new Translator(fileName);
        Exception thrown = Assertions.assertThrows(InvalidInstructionException.class,
                () -> translator.readAndTranslate(
                        machine.getLabels(), machine.getProgram())
        );
    }

    @Test
    void invalidRegisterThrows() {
        String errorType = "invalidRegister";
        String fileName = filePath + errorType + extension;
        Translator translator = new Translator(fileName);
        Exception thrown = Assertions.assertThrows(InvalidInstructionException.class,
                () -> translator.readAndTranslate(
                        machine.getLabels(), machine.getProgram())
        );
    }

    @Test
        // setting int value > int.MAX_VALUE
    void largeNumberThrows() {
        String errorType = "largeNumber";
        String fileName = filePath + errorType + extension;
        Translator translator = new Translator(fileName);
        Exception thrown = Assertions.assertThrows(InvalidInstructionException.class,
                () -> translator.readAndTranslate(
                        machine.getLabels(), machine.getProgram())
        );
    }

    @Test
    void tooFewArgsThrows() {
        String errorType = "tooFewArgs";
        String fileName = filePath + errorType + extension;
        Translator translator = new Translator(fileName);
        Exception thrown = Assertions.assertThrows(InvalidInstructionException.class,
                () -> translator.readAndTranslate(
                        machine.getLabels(), machine.getProgram())
        );
    }

    @Test
    void tooManyArgsThrows() {
        String errorType = "tooManyArgs";
        String fileName = filePath + errorType + extension;
        Translator translator = new Translator(fileName);
        Exception thrown = Assertions.assertThrows(InvalidInstructionException.class,
                () -> translator.readAndTranslate(
                        machine.getLabels(), machine.getProgram())
        );
    }

    @Test
    void duplicateLabelThrows() {
        String errorType = "duplicateLabel";
        String fileName = filePath + errorType + extension;
        Translator translator = new Translator(fileName);
        Exception thrown = Assertions.assertThrows(DuplicateLabelException.class,
                () -> translator.readAndTranslate(
                        machine.getLabels(), machine.getProgram())
        );
    }
}
