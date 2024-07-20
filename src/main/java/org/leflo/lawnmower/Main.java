package org.leflo.lawnmower;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if(args.length != 1){
            LOGGER.severe("Usage: java -jar LawnMower.jar <file>");
            System.exit(1);
            return;
        }

        try(LawnMowerProgram program = new LawnMowerProgram(new File(args[0]))){
            LawnMowerExecution execution = new LawnMowerExecution(program);
            execution.run();
        } catch (IOException e) {
            LOGGER.severe("An error occurred while reading the file: " + e.getMessage());
        }
    }

}
