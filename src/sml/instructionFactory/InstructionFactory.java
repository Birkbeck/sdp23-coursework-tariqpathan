package sml.instructionFactory;

import sml.Instruction;

public interface InstructionFactory {
    Instruction create(String label, String[] args);
}
