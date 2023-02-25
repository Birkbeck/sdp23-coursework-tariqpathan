package sml;

import org.junit.jupiter.api.*;

public class MainTest {
    private Machine machine;
    private Registers registers;
    private String TEST_RESOURCES_ROOT = "../resources/";


    @BeforeEach
    void setup() {
        Machine machine = new Machine(new Registers());

    }


}
