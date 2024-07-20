package org.leflo.lawnmower;

/**
 * Represents the execution of a lawn mower program
 */
public class LawnMowerExecution {

    private static final char LEFT = 'G';
    private static final char RIGHT = 'D';
    private static final char FORWARD = 'A';

    private final LawnMowerProgram program;

    public LawnMowerExecution(LawnMowerProgram program){
        this.program = program;
    }

    /**
     * Run the lawn mower program
     */
    public void run(){
        Lawn lawn = program.readLawn();

        while(program.hasNext()){
            LawnMower lawnMower = program.readLawnMower();
            String instructions = program.readInstructions();

            this.processInstruction(lawn, lawnMower, instructions);

            lawn.addLawnMower(lawnMower);
        }

        lawn.getLawnMowers().forEach(lawnMower -> System.out.println(lawnMower.toString()));
    }

    /**
     * Process the instruction for a lawn mower
     * @param lawn the lawn where the lawn mower is
     * @param lawnMower the lawn mower
     * @param instructions the instructions
     */
    public void processInstruction(Lawn lawn, LawnMower lawnMower, String instructions){
        instructions.chars().forEach(instruction -> {
            switch (instruction){
                case LEFT:
                    lawnMower.turnLeft();
                    break;
                case RIGHT:
                    lawnMower.turnRight();
                    break;
                case FORWARD:
                    if(lawn.canLawnMowerMove(lawnMower)){
                        lawnMower.move();
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown instruction: " + instruction);
            }
        });
    }

}
