import board.Board;
import board.BoardFactory;
import board.CellType;

import org.junit.Test;
import static org.junit.Assert.*;

public class boardTest {
    @Test
    public void testBoardCreation4x4() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.4x4");

        assertEquals(board.getRows().size(), 4);
        assertEquals(board.getRows().get(0).getCells().size(), 4);
        assertEquals(board.getBoxHeight(), 2);
        assertEquals(board.getBoxWidth(), 2);

        // the numbers in puzzle.4x4 -- 0340400210030210
        int[] nums = {0, 3, 4, 0, 4, 0, 0, 2, 1, 0, 0, 3, 0, 2, 1, 0};

        for (int y = 0; y < board.getRows().size(); y++) {
            for (int x = 0; x < board.getRows().get(y).getCells().size(); x++) {
                assertEquals(board.getCell(y, x).getValue(), nums[y * board.getRows().size() + x]);
            }
        }
    }

    @Test
    public void testBoardCreation6x6() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.6x6");

        assertEquals(board.getRows().size(), 6);
        assertEquals(board.getRows().get(0).getCells().size(), 6);
        assertEquals(board.getBoxHeight(), 2);
        assertEquals(board.getBoxWidth(), 3);

        // the numbers in puzzle.6x6 -- 003010560320054203206450012045040100
        int[] nums = {0, 0, 3, 0, 1, 0, 5, 6, 0, 3, 2, 0, 0, 5, 4, 2, 0, 3, 2, 0, 6, 4, 5, 0, 0, 1, 2, 0, 4, 5, 0, 4, 0, 1, 0, 0};

        for (int y = 0; y < board.getRows().size(); y++) {
            for (int x = 0; x < board.getRows().get(y).getCells().size(); x++) {
                assertEquals(board.getCell(y, x).getValue(), nums[y * board.getRows().size() + x]);
            }
        }
    }

    @Test
    public void testBoardCreation9x9() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.9x9");

        assertEquals(board.getRows().size(), 9);
        assertEquals(board.getRows().get(0).getCells().size(), 9);
        assertEquals(board.getBoxHeight(), 3);
        assertEquals(board.getBoxWidth(), 3);

        // the numbers in puzzle.9x9 -- 700509001000000000150070063003904100000050000002106400390040076000000000600201004
        int[] nums = {7,0,0,5,0,9,0,0,1,0,0,0,0,0,0,0,0,0,1,5,0,0,7,
            0,0,6,3,0,0,3,9,0,4,1,0,0,0,0,0,0,5,0,0,0,0,0,0,2,1,0,6,4,
            0,0,3,9,0,0,4,0,0,7,6,0,0,0,0,0,0,0,0,0,6,0,0,2,0,1,0,0,4};

        for (int y = 0; y < board.getRows().size(); y++) {
            for (int x = 0; x < board.getRows().get(y).getCells().size(); x++) {
                assertEquals(board.getCell(y, x).getValue(), nums[y * board.getRows().size() + x]);
            }
        }
    }  

    @Test
    public void testAddingToBoard() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.4x4");
        // the numbers in puzzle.4x4 -- 0340400210030210

        // try to change a given cell -- this doesnt do anything
        board.setCell(0, 1, 1, CellType.FINAL);
        assertEquals(board.getCell(0, 1).getValue(), 3);
        assertEquals(board.getCell(0, 1).getType(), CellType.GIVEN);

        // try to change a final cell -- this should change
        board.setCell(0, 0, 1, CellType.FINAL);
        assertEquals(board.getCell(0, 0).getValue(), 1);
        assertEquals(board.getCell(0, 0).getType(), CellType.FINAL);

        // enter the same number into the cell -- this should change it to 0
        board.setCell(0, 0, 1, CellType.FINAL);
        assertEquals(board.getCell(0, 0).getValue(), 0);
        assertEquals(board.getCell(0, 0).getType(), CellType.EMPTY);
    }
}
