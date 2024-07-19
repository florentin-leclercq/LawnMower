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
            new LawnMowerProgram(new File("src/test/resources/unknownFile.txt"));
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
            for(int i = 0; i < lines; i++){
                Assertions.assertTrue(program.hasNext());
            }
            Assertions.assertFalse(program.hasNext());
        }

    }

    /**
     * Tests {@link LawnMowerProgram#readLawnSize()} with valid inputs.
     */
    @Test
    public void testReadLawnSize() throws IOException {
        try(LawnMowerProgram program = new LawnMowerProgram(new File("src/test/resources/program.txt"))){
            Assertions.assertTrue(program.hasNext());
            int[] lawnSize = program.readLawnSize();
            Assertions.assertEquals(5, lawnSize[0]);
            Assertions.assertEquals(5, lawnSize[1]);
        }
    }

    /**
     * Tests {@link LawnMowerProgram#readLawnMower()} with valid inputs.
     */
    @Test
    public void testReadLawnMower() throws IOException {
        try(LawnMowerProgram program = new LawnMowerProgram(new File("src/test/resources/program.txt"))){
            Assertions.assertTrue(program.hasNext());
            program.readLawnSize();
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
            Assertions.assertTrue(program.hasNext());
            program.readLawnSize();
            Assertions.assertTrue(program.hasNext());
            program.readLawnMower();
            Assertions.assertTrue(program.hasNext());
            String instructions = program.readInstructions();
            Assertions.assertEquals("GAGAGAGAA", instructions);
        }
    }
    
}
