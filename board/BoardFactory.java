package board;

import java.util.ArrayList;
import java.util.Random;

public class BoardFactory {
    public Board createBoard() {
        Board board = new Board(9, 9);
        Random random = new Random();
        
        ArrayList<CellHolder> rows = board.getRows();
        for (int y = 0; y < rows.size(); y++) {
            ArrayList<Cell> cells = board.getRows().get(y).getCells();
            for (int x = 0; x < cells.size(); x++) {
                board.setCell(y, x, random.nextInt(10), CellType.FINAL);
            }
        }

        return board;
    }
}
