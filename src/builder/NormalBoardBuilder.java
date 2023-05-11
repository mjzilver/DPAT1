package builder;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellType;

import java.util.ArrayList;
import java.util.List;

public class NormalBoardBuilder implements IBoardBuilder {
    private final int _col;
    private final int _row;
    private final int _boxWidth;
    private final int _boxHeight;
    private final int _maxNumber;

    public NormalBoardBuilder(int col, int row, int boxWidth, int boxHeight, int maxNumber) {
        _col = col;
        _row = row;
        _boxWidth = boxWidth;
        _boxHeight = boxHeight;
        _maxNumber = maxNumber;
    }

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(_col, _row, _boxWidth, _boxHeight, _maxNumber);

        ArrayList<CellHolder> rows = new ArrayList<>();
        ArrayList<CellHolder> cols = new ArrayList<>();
        ArrayList<CellHolder> boxes = new ArrayList<>();

        for (int i = 0; i < _row; i++) {
            CellHolder row = new CellHolder();
            rows.add(row);

            for (int j = 0; j < _col; j++) {
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
        for (int i = 0; i < _boxHeight - 1; i++) {
            for (int j = 0; j < _boxWidth - 1; j++) {
                CellHolder box = new CellHolder();
                boxes.add(box);

                for (int k = 0; k < _boxHeight - 1; k++) {
                    CellHolder row = rows.get(i * _boxHeight + k);
                    for (int l = 0; l < _boxWidth - 1; l++) {
                        Cell cell = row.get(j * _boxWidth + l);
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
        for (int y = 0; y < _row; y++) {
            for (int x = 0; x < _col; x++) {
                board.setCell(y, x, nums.get(index++), CellType.GIVEN);
            }
        }

        return board;
    }
}
