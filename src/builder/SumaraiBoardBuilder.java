package builder;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellType;

public class SumaraiBoardBuilder implements IBoardBuilder {
    private static final int ROWS = 9;
    private static final int COLS = 9;
    private static final int SETS = 5;
    private static final int BOXHEIGHT = 3;
    private static final int BOXWIDTH = 3;

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(ROWS, COLS, 9);

        ArrayList<CellHolder> rows = new ArrayList<>();
        ArrayList<CellHolder> cols = new ArrayList<>();
        ArrayList<CellHolder> boxes = new ArrayList<>();

        for (int i = 0; i < ROWS; i++) {
            CellHolder row = new CellHolder();
            rows.add(row);

            for (int j = 0; j < COLS; j++) {
                Cell cell = new Cell(0);
                row.addCell(cell);

                if (cols.size() <= j) {
                    CellHolder newColumn = new CellHolder();
                    cols.add(newColumn);
                }

                // Add the cell to the column
                CellHolder currentColumn = cols.get(j);
                currentColumn.addCell(cell);
            }
        }

        // filling the boxes inside the board
        for (int i = 0; i < ROWS / BOXHEIGHT; i++) {
            for (int j = 0; j < COLS / BOXWIDTH; j++) {
                CellHolder box = new CellHolder();
                boxes.add(box);

                for (int k = 0; k < BOXHEIGHT; k++) {
                    CellHolder row = rows.get(i * BOXHEIGHT + k);
                    for (int l = 0; l < BOXWIDTH; l++) {
                        Cell cell = row.get(j * BOXWIDTH + l);
                        box.addCell(cell);
                    }
                }
            }
        }

        board.setColumns(cols);
        board.setRows(rows);
        board.setBoxes(boxes);
        
        ArrayList<Integer> nums = new ArrayList<>();
        for (String line : fileContent) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                nums.add(Character.getNumericValue(c));
            }
        }
        int index = 0;
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                board.setCell(y, x, nums.get(index++), CellType.GIVEN);
            }
        }

        return board;
    }

}
