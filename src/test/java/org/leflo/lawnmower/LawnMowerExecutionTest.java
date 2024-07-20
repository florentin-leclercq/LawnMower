package org.leflo.lawnmower;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class LawnMowerExecutionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }


    /**
     * Tests {@link LawnMowerExecution#run()} with a mock of 2 lawn mowers
     * @throws IOException
     */
    @Test
    public void testRun() throws IOException {
        // Mock creation
        LawnMower lawnMowerMock1 = Mockito.mock(LawnMower.class);
        Mockito.when(lawnMowerMock1.toString()).thenReturn("1 2 N");

        LawnMower lawnMowerMock2 = Mockito.mock(LawnMower.class);
        Mockito.when(lawnMowerMock2.toString()).thenReturn("3 3 E");

        Lawn lawnMock = Mockito.mock(Lawn.class);
        Mockito.when(lawnMock.canLawnMowerMove(Mockito.any(LawnMower.class))).thenReturn(true);
        Mockito.when(lawnMock.getLawnMowers()).thenReturn(Arrays.asList(lawnMowerMock1, lawnMowerMock2));

        try(LawnMowerProgram lawnMowerProgramMock = Mockito.mock(LawnMowerProgram.class)){
            Mockito.when(lawnMowerProgramMock.readLawn()).thenReturn(lawnMock);
            Mockito.when(lawnMowerProgramMock.hasNext()).thenReturn(true, true, false);

            Mockito.when(lawnMowerProgramMock.readLawnMower()).thenReturn(lawnMowerMock1, lawnMowerMock2);
            Mockito.when(lawnMowerProgramMock.readInstructions()).thenReturn("GAGAGAGAA", "AADAADADDA");

            LawnMowerExecution lawnMowerExecution = Mockito.spy(new LawnMowerExecution(lawnMowerProgramMock));
            Mockito.doCallRealMethod().when(lawnMowerExecution).run();

            // Test
            lawnMowerExecution.run();

            String[] writtenLines = outContent.toString().split(System.lineSeparator());
            Assertions.assertEquals(2, writtenLines.length);
            Assertions.assertEquals("1 2 N", writtenLines[0]);
            Assertions.assertEquals("3 3 E", writtenLines[1]);

            // Verify
            Mockito.verify(lawnMowerProgramMock, Mockito.times(1)).readLawn();
            Mockito.verify(lawnMowerProgramMock, Mockito.times(3)).hasNext();
            Mockito.verify(lawnMowerProgramMock, Mockito.times(2)).readLawnMower();
            Mockito.verify(lawnMock, Mockito.times(1)).addLawnMower(lawnMowerMock1);
            Mockito.verify(lawnMock, Mockito.times(1)).addLawnMower(lawnMowerMock2);
            Mockito.verify(lawnMowerExecution, Mockito.times(1)).processInstruction(lawnMock, lawnMowerMock1, "GAGAGAGAA");
            Mockito.verify(lawnMowerExecution, Mockito.times(1)).processInstruction(lawnMock, lawnMowerMock2, "AADAADADDA");
        }
    }

    @Test
    public void testProcessInstructionThrowError() {
        // Mock creation
        Lawn lawnMock = Mockito.mock(Lawn.class);
        LawnMower lawnMowerMock = Mockito.mock(LawnMower.class);

        String instructions = "INVALID";

        // Test
        LawnMowerExecution lawnMowerExecution = new LawnMowerExecution(Mockito.mock(LawnMowerProgram.class));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lawnMowerExecution.processInstruction(lawnMock, lawnMowerMock, instructions);
        });
        Mockito.verify(lawnMowerMock, Mockito.never()).move();
        Mockito.verify(lawnMowerMock, Mockito.never()).turnLeft();
        Mockito.verify(lawnMowerMock, Mockito.never()).turnRight();
    }

    @Test
    public void testProcessInstructionCannotMove() {
        // Mock creation
        Lawn lawnMock = Mockito.mock(Lawn.class);
        Mockito.when(lawnMock.canLawnMowerMove(Mockito.any(LawnMower.class))).thenReturn(false);

        LawnMower lawnMowerMock = Mockito.mock(LawnMower.class);

        String instructions = "A";

        // Test
        LawnMowerExecution lawnMowerExecution = new LawnMowerExecution(Mockito.mock(LawnMowerProgram.class));
        lawnMowerExecution.processInstruction(lawnMock, lawnMowerMock, instructions);

        // Verify
        Mockito.verify(lawnMock, Mockito.times(1)).canLawnMowerMove(lawnMowerMock);
        Mockito.verify(lawnMowerMock, Mockito.never()).move();
        Mockito.verify(lawnMowerMock, Mockito.never()).turnLeft();
        Mockito.verify(lawnMowerMock, Mockito.never()).turnRight();
    }

    @Test
    public void testProcessInstructionSequential() {
        // Mock creation
        Lawn lawnMock = Mockito.mock(Lawn.class);
        Mockito.when(lawnMock.canLawnMowerMove(Mockito.any(LawnMower.class))).thenReturn(true);

        LawnMower lawnMowerMock = Mockito.mock(LawnMower.class);

        String instructions = "AAGDAG";

        // Test
        LawnMowerExecution lawnMowerExecution = new LawnMowerExecution(Mockito.mock(LawnMowerProgram.class));
        lawnMowerExecution.processInstruction(lawnMock, lawnMowerMock, instructions);

        // Verify
        Mockito.verify(lawnMock, Mockito.times(3)).canLawnMowerMove(lawnMowerMock);
        Mockito.verify(lawnMowerMock, Mockito.times(3)).move();
        Mockito.verify(lawnMowerMock, Mockito.times(2)).turnLeft();
        Mockito.verify(lawnMowerMock, Mockito.times(1)).turnRight();
    }

}
