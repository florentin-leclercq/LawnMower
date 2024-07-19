package org.leflo.lawnmower;

import java.io.*;

/**
 * Represents a lawn mower program that reads a file containing lawn size, lawn mowers and instructions
 */
public class LawnMowerProgram implements AutoCloseable {

    private File file;
    private BufferedReader reader;

    private String currentLine;

    public LawnMowerProgram(File programFile) throws FileNotFoundException {
        this.file = programFile;
        this.reader = new BufferedReader(new FileReader(programFile));
    }

    /**
     * Reads the lawn size from the current line
     * @return an array containing the width and height of the lawn
     */
    public int[] readLawnSize(){
        String[] parts = currentLine.split(" ");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }

    /**
     * Reads the lawn mower from the current line
     * @return a LawnMower object
     */
    public LawnMower readLawnMower(){
        String[] parts = currentLine.split(" ");
        return new LawnMower(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Direction.valueOf(String.valueOf(parts[2].charAt(0))));
    }

    /**
     * Reads the instructions from the current line
     * @return a string containing the instructions
     */
    public String readInstructions(){
        return currentLine;
    }

    /**
     * Reads the next line of the program file
     * @return true if there is a next line, false otherwise
     */
    public boolean hasNext(){
        try {
            return (currentLine = reader.readLine()) != null;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Closes the file reader
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }

}
