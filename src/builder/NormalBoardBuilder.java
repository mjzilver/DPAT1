package builder;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellType;

import java.util.ArrayList;
import java.util.List;

public class NormalBoardBuilder implements IBoardBuilder {
    private final int col;
    private final int row;
    private final int boxWidth;
    private final int boxHeight;
    private final int maxNumber;

    public NormalBoardBuilder(int col, int row, int boxWidth, int boxHeight, int maxNumber) {
        this.col = col;
        this.row = row;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.maxNumber = maxNumber;
    }

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(col, row, boxWidth, boxHeight, maxNumber);

        ArrayList<CellHolder> rows = new ArrayList<>();
        ArrayList<CellHolder> cols = new ArrayList<>();
        ArrayList<CellHolder> boxes = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            CellHolder row = new CellHolder();
            rows.add(row);

            for (int j = 0; j < col; j++) {
                Cell cell = board.getCell(i, j);
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
        for (int i = 0; i < row / boxHeight; i++) {
            for (int j = 0; j < col / boxWidth; j++) {
                CellHolder box = new CellHolder();
                boxes.add(box);

                for (int k = 0; k < boxHeight; k++) {
                    CellHolder row = rows.get(i * boxHeight + k);
                    for (int l = 0; l < boxWidth; l++) {
                        Cell cell = row.get(j * boxWidth + l);
                        box.addCell(cell);
                    }
                }
            }
        }

        board.setColumns(cols);
        board.setRows(rows);
        board.setBoxes(boxes);

        int index = 0;

        for (String line : fileContent) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                int num = Character.getNumericValue(c);
                board.setCell(index / col, index % col, num, CellType.GIVEN);
                index++;
            }
        }

        return board;
    }
}
