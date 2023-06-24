import board.Board;
import board.BoardFactory;
import board.CellType;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class BoardTest {
    @Test
    public void testBoardCreation4x4() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.4x4");

        assertEquals(board.getRows().size(), 4);
        assertEquals(board.getRows().get(0).getCells().size(), 4);
        assertEquals(board.getBoxHeight(), 2);
        assertEquals(board.getBoxWidth(), 2);

        // the numbers in puzzle.4x4 -- 0340400210030210
        int[] nums = { 0, 3, 4, 0, 4, 0, 0, 2, 1, 0, 0, 3, 0, 2, 1, 0 };

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
        int[] nums = { 0, 0, 3, 0, 1, 0, 5, 6, 0, 3, 2, 0, 0, 5, 4, 2, 0, 3, 2, 0, 6, 4, 5, 0, 0, 1, 2, 0, 4, 5, 0, 4,
                0, 1, 0, 0 };

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

        // the numbers in puzzle.9x9 --
        // 700509001000000000150070063003904100000050000002106400390040076000000000600201004
        int[] nums = { 7, 0, 0, 5, 0, 9, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5, 0, 0, 7,
                0, 0, 6, 3, 0, 0, 3, 9, 0, 4, 1, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 2, 1, 0, 6, 4,
                0, 0, 3, 9, 0, 0, 4, 0, 0, 7, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 2, 0, 1, 0, 0, 4 };

        for (int y = 0; y < board.getRows().size(); y++) {
            for (int x = 0; x < board.getRows().get(y).getCells().size(); x++) {
                assertEquals(board.getCell(y, x).getValue(), nums[y * board.getRows().size() + x]);
            }
        }
    }

    @Test
    public void testBoardCreationJigsaw() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.jigsaw");

        assertEquals(board.getRows().size(), 9);
        assertEquals(board.getRows().get(0).getCells().size(), 9);

        // add more tests here
    }

    @Test
    public void testBoardCreationSamurai() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoard("puzzle.samurai");

        assertEquals(board.getWidth(), 21);
        assertEquals(board.getHeight(), 21);

        ArrayList<int[]> subboards = new ArrayList<>();

        String input = "800000700003050206700300095000091840000007002000062000000000000609080000002903000";
        int[] subboard = input.chars().map(Character::getNumericValue).toArray();
        subboards.add(0, subboard);

        input = "149000000000091000000060000007120008000000340405008067000000000000007020000050003";
        subboard = input.chars().map(Character::getNumericValue).toArray();
        subboards.add(1, subboard);

        input = "000000000000008000000004000010600005030070080800005010000900000000800000000000000";
        subboard = input.chars().map(Character::getNumericValue).toArray();
        subboards.add(2, subboard);

        input = "900060000030400000000000000390800407065000000200037600000080000000190000000000914";
        subboard = input.chars().map(Character::getNumericValue).toArray();
        subboards.add(3, subboard);

        input = "000402800000080902000000000000610000400800000098750000670008001901060700002000009";
        subboard = input.chars().map(Character::getNumericValue).toArray();
        subboards.add(4, subboard);

        
        for (int i = 0; i < subboards.size(); i++) {
            int xOffset = 0;
            int yOffset = 0;

            if (i == 1) {
                xOffset = 12;
            } else if (i == 2) {
                yOffset = 6;
                xOffset = 6;
            } else if (i == 3) {
                yOffset = 12;
            } else if (i == 4) {
                yOffset = 12;
                xOffset = 12;
            }
            
            int[] nums = subboards.get(i);
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    assertEquals(board.getCell(y + yOffset, x + xOffset).getValue(), nums[y * 9 + x]);
                }
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
