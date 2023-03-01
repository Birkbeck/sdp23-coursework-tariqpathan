package sml.instructionFactory;

import sml.Instruction;

public interface InstructionFactory {
    Instruction create(String[] args);
}
