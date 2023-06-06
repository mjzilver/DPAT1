package builder;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellType;

import java.util.ArrayList;
import java.util.List;

public class SamuraiBoardBuilder implements IBoardBuilder {
    private static final int WIDTH = 21;
    private static final int HEIGHT = 21;
    private static final int SUBWIDTH = 9;
    private static final int SUBHEIGHT = 9;
    private static final int BOXWIDTH = 3;
    private static final int boxHeight = 3;

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(WIDTH, HEIGHT, 9); // 21x21 board with 3x3 boxes

        ArrayList<CellHolder> rows = new ArrayList<>();
        ArrayList<CellHolder> cols = new ArrayList<>();
        ArrayList<CellHolder> boxes = new ArrayList<>();

        for (int i = 0; i < HEIGHT; i++) {
            CellHolder row = new CellHolder();
            rows.add(row);

            for (int j = 0; j < WIDTH; j++) {
                Cell cell = new Cell(0);
                cell.setType(CellType.INACTIVE);
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
        for (int i = 0; i < HEIGHT / boxHeight; i++) {
            for (int j = 0; j < WIDTH / BOXWIDTH; j++) {
                CellHolder box = new CellHolder();
                boxes.add(box);

                for (int k = 0; k < boxHeight; k++) {
                    CellHolder row = rows.get(i * boxHeight + k);
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

        // Fill in the given numbers from the fileContent
        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            int xOffset = 0;
            int yOffset = 0;

            /*
             * regel 0 = linksboven
             * regel 1 = rechtsboven
             * regel 2 = midden
             * regel 3 = linksonder
             * regel 4 = rechtsonder
             */
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

            for (int j = 0; j < line.length(); j++) {
                int num = Character.getNumericValue(line.charAt(j));
                int x = (j % SUBWIDTH) + xOffset;
                int y = (j / SUBHEIGHT) + yOffset;

                if (num != 0) {
                    board.setCell(y, x, num, CellType.GIVEN);
                } else {
                    board.setCell(y, x, 0, CellType.EMPTY);
                }
            }
        }

        return board;
    }
}
