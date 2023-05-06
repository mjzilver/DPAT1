import board.Board;
import board.BoardFactory;

import org.junit.Test;
import static org.junit.Assert.*;

public class boardTest {
    // write tests for the builder class

    @Test
    public void testBoardCreation() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.4x4");

        assertEquals(board.getRows().size(), 4);
        assertEquals(board.getRows().get(0).getCells().size(), 4);
        
    }
}
