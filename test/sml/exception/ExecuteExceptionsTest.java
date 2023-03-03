package sml.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Registers;
import sml.Translator;

public class ExecuteExceptionsTest {

    private Machine machine;
    private final String filePath = "./test-resources/throwsExceptionExecute/";
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
    void divideByZeroThrows() throws Exception {
        String errorType = "divideByZero";
        String fileName = filePath + errorType + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Exception thrown = Assertions.assertThrows(ArithmeticException.class,
                () -> machine.execute());
    }

    @Test
    void invalidLabelThrows() throws Exception {
        String errorType = "invalidLabel";
        String fileName = filePath + errorType + extension;
        Translator translator = new Translator(fileName);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        Exception thrown = Assertions.assertThrows(NullPointerException.class,
                () -> machine.execute());
    }
}
