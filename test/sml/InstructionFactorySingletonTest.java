package sml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstructionFactorySingletonTest {

    @Test
    void factoryInstancesEqual() {
        InstructionFactory instructionFactory1 = InstructionFactory.getInstance();
        InstructionFactory instructionFactory2 = InstructionFactory.getInstance();
        Assertions.assertEquals(instructionFactory1, instructionFactory2);
    }

}
