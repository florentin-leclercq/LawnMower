package org.leflo.lawnmower;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class LawnMowerProgramTest {

    /**
     * Tests {@link LawnMowerProgram#LawnMowerProgram(File)} with a file that does not exist.
     */
    @Test
    public void testLawnMowerProgramConstructorThrowsFileNotFoundException() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            LawnMowerProgram program = new LawnMowerProgram(new File("src/test/resources/doesnotexist.txt"));
        });
    }

    /**
     * Tests {@link LawnMowerProgram#hasNext()}.
     */
    @Test
    public void testHasNext() throws IOException {
        File file = new File("src/test/resources/program.txt");
        long lines;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            lines = reader.lines().count();
        }
        try(LawnMowerProgram program = new LawnMowerProgram(file)){
            program.readLawn();
            for(int i = 0; i < (lines-1)/2; i++){
                Assertions.assertTrue(program.hasNext());
                program.readLawnMower();
                Assertions.assertTrue(program.hasNext());
                program.readInstructions();
            }
            Assertions.assertFalse(program.hasNext());
        }
    }

    /**
     * Tests {@link LawnMowerProgram#readLawn()}.
     */
    @Test
    public void testReadLawn() throws IOException {
        try(LawnMowerProgram program = new LawnMowerProgram(new File("src/test/resources/program.txt"))){
            Lawn lawn = program.readLawn();
            Assertions.assertEquals(6, lawn.getWidth());
            Assertions.assertEquals(6, lawn.getHeight());
        }
    }

    /**
     * Tests {@link LawnMowerProgram#readLawnMower()}.
     */
    @Test
    public void testReadLawnMower() throws IOException {
        try(LawnMowerProgram program = new LawnMowerProgram(new File("src/test/resources/program.txt"))){
            program.readLawn();
            Assertions.assertTrue(program.hasNext());
            LawnMower lawnMower = program.readLawnMower();
            Assertions.assertEquals(1, lawnMower.getX());
            Assertions.assertEquals(2, lawnMower.getY());
            Assertions.assertEquals(Direction.N, lawnMower.getDirection());
        }
    }

    /**
     * Tests {@link LawnMowerProgram#readInstructions()} with valid inputs.
     */
    @Test
    public void testReadInstructions() throws IOException {
        try(LawnMowerProgram program = new LawnMowerProgram(new File("src/test/resources/program.txt"))){
            program.readLawn();
            Assertions.assertTrue(program.hasNext());
            program.readLawnMower();
            Assertions.assertTrue(program.hasNext());
            String instructions = program.readInstructions();
            Assertions.assertEquals("GAGAGAGAA", instructions);
        }
    }
    
}
