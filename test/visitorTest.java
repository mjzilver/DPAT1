import board.Board;
import board.BoardFactory;
import board.CellStatus;
import visitor.Visitor;
import org.junit.Test;
import static org.junit.Assert.*;

public class visitorTest {

    @Test
    // Test the visitor on a 4x4 board
    public void testVisitor4x4() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.4x4");
        Visitor visitor = new Visitor();

        // the numbers in puzzle.4x4 -- 0340400210030210
        // this number is wrong
        board.getCell(0, 3).setValue(3);
        // this number is correct
        board.getCell(1, 2).setValue(3);

        visitor.checkBoard(board);

        assertEquals(board.getCell(0, 3).getStatus(), CellStatus.WRONG);
        assertEquals(board.getCell(1, 2).getStatus(), CellStatus.CORRECT);
        // given numbers are always good
        assertEquals(board.getCell(0, 1).getStatus(), CellStatus.CORRECT);
        assertEquals(board.getCell(0, 2).getStatus(), CellStatus.CORRECT);    
    }
}
