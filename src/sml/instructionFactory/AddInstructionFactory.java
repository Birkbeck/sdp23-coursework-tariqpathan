package sml.instructionFactory;

import sml.instruction.AddInstruction;

public class AddInstructionFactory implements InstructionFactory{

    // get Spring to link the Factory to the Instruction
    @Override
    public AddInstruction create(String label, String[] args) {

        return null;
    }
}
