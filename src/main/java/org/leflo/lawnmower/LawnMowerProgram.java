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
        this.readLine();
    }

    /**
     * Reads the lawn from the current line
     * @return a Lawn object
     */
    public Lawn readLawn(){
        String[] parts = currentLine.split(" ");
        this.readLine();
        return new Lawn(Integer.parseInt(parts[0])+1, Integer.parseInt(parts[1])+1);
    }

    /**
     * Reads the lawn mower from the current line
     * @return a LawnMower object
     */
    public LawnMower readLawnMower(){
        String[] parts = currentLine.split(" ");
        this.readLine();
        return new LawnMower(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Direction.valueOf(String.valueOf(parts[2].charAt(0))));
    }

    /**
     * Reads the instructions from the current line
     * @return a string containing the instructions
     */
    public String readInstructions(){
        String line = this.currentLine;
        this.readLine();
        return line;
    }

    private void readLine() {
        try {
            currentLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return true if there is a next line, false otherwise
     */
    public boolean hasNext(){
        return currentLine != null;
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
