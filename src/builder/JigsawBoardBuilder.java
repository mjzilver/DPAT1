package builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellType;

public class JigsawBoardBuilder implements IBoardBuilder {
    private static final int ROWS = 9;
    private static final int COLS = 9;

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(9, 9, 9);

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

            // add empty boxes
            CellHolder box = new CellHolder();
            boxes.add(box);
        }

        board.setColumns(cols);
        board.setRows(rows);

        for (String line : fileContent) {
            String[] tokens = line.split("=");
            // skip the 1st item as it is "SumoCueV1"
            tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
            for (int i = 0; i < tokens.length; i++) {
                String[] cellTokens = tokens[i].split("J");
                int y = i / 9;
                int x = i % 9;
                int value = Integer.parseInt(cellTokens[0]);
                int boxIndex = Integer.parseInt(cellTokens[1]);
                board.setCell(y, x, value, CellType.GIVEN);

                if (boxes.size() <= y) {
                    boxes.add(new CellHolder());
                }   

                CellHolder currentBox = boxes.get(boxIndex);
                Cell cell = board.getCell(y, x);
                currentBox.addCell(cell);
            }
            
        }
        board.setBoxes(boxes);
        return board;
    }
}
