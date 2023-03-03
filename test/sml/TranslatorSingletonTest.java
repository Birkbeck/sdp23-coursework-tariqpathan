package sml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TranslatorSingletonTest {

    @Test
    void singleInstance() {
//        Translator t = new Translator(null);
//        InstructionFactory instructionFactory1 = InstructionFactory.getInstance();
        InstructionFactory instructionFactory2 = new InstructionFactory();
        InstructionFactory instructionFactory3 = new InstructionFactory();
        Assertions.assertEquals(instructionFactory2, instructionFactory3);

    }

}
