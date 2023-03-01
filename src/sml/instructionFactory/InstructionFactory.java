package sml.instructionFactory;

import sml.Instruction;

interface InstructionFactory {
    Instruction create(String label, String[] args);
}
