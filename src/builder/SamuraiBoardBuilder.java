package builder;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellType;

import java.util.ArrayList;
import java.util.List;

public class SamuraiBoardBuilder implements IBoardBuilder {
    private final int _maxNumber = 9;

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(21, 21, 3, 3, _maxNumber); // 21x21 board with 3x3 boxes

        ArrayList<CellHolder> rows = new ArrayList<>();
        ArrayList<CellHolder> cols = new ArrayList<>();
        ArrayList<CellHolder> boxes = new ArrayList<>();

        // Create cells for the main grids
        for (int i = 0; i < 9; i++) {
            CellHolder row = new CellHolder();
            CellHolder col = new CellHolder();
            CellHolder box = new CellHolder();    

            rows.add(row);
            cols.add(col);
            boxes.add(box);

            for (int j = 0; j < 9; j++) {
                Cell cell = new Cell(0);
                row.addCell(cell);
                col.addCell(cell);
                box.addCell(cell);
            }
        }

        // Assign the main grid cells to the board
        board.setColumns(cols);
        board.setRows(rows);
        board.setBoxes(boxes);

        // Create cells for the additional grids
        for (int i = 9; i < 21; i++) {
            CellHolder row = new CellHolder();
            CellHolder col = new CellHolder();
            CellHolder box = new CellHolder();

            rows.add(row);
            cols.add(col);
            boxes.add(box);

            for (int j = 0; j < 21; j++) {
                Cell cell = new Cell(0);
                row.addCell(cell);
                col.addCell(cell);
                box.addCell(cell);
            }
        }

        // Fill in the given numbers from the fileContent
        ArrayList<Integer> nums = new ArrayList<>();
        for (String line : fileContent) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                nums.add(Character.getNumericValue(c));
            }
        }

        int index = 0;

        // Fill the main grids
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                board.setCell(y, x, nums.get(index++), CellType.GIVEN);
            }
        }

        // Fill the additional grids
        for (int i = 9; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                board.setCell(i, j, nums.get(index++), CellType.GIVEN);
            }
        }

        return board;
    }
}
