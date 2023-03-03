package instructionFactory;

import org.junit.jupiter.api.*;
import sml.Machine;
import sml.Registers;

class ReadAndCreateExceptionsThrownTest {
    private Machine machine;
    private String filePath = "./test-resources/throwsException/";
    private String extension = ".sml";

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
    }

    @AfterEach
    void tearDown() {
        machine = null;
    }
}
