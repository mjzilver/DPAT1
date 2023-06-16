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
    private static final int BOXHEIGHT = 3;
    private static final int SUBBOARDS = 5;

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(WIDTH, HEIGHT, 9, 9, 9); // 21x21 board with 3x3 boxes

        ArrayList<CellHolder> rows = new ArrayList<>();
        ArrayList<CellHolder> cols = new ArrayList<>();
        ArrayList<CellHolder> boxes = new ArrayList<>();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Cell cell = board.getCell(y, x);
                cell.setType(CellType.INACTIVE);
            }
        }

        for (int s = 0; s < SUBBOARDS; s++) {
            int xOffset = 0;
            int yOffset = 0;

            if (s == 1) {
                xOffset = 12;
            } else if (s == 2) {
                yOffset = 6;
                xOffset = 6;
            } else if (s == 3) {
                yOffset = 12;
            } else if (s == 4) {
                yOffset = 12;
                xOffset = 12;
            }

            for (int y = 0; y < SUBHEIGHT; y++) {
                CellHolder row = new CellHolder();
                rows.add(row);

                for (int x = 0; x < SUBWIDTH; x++) {
                    int cellY = y + yOffset;
                    int cellX = x + xOffset;
                    Cell cell = board.getCell(cellY, cellX);

                    row.addCell(cell);

                    int colNum = x + (s * SUBWIDTH);
                    if (cols.size() <= colNum) {
                        CellHolder newColumn = new CellHolder();
                        cols.add(newColumn);
                    }

                    // Add the cell to the column
                    CellHolder currentColumn = cols.get(colNum);
                    currentColumn.addCell(cell);

                    // Fill in the given numbers from the fileContent
                    String line = fileContent.get(s);
                    int num = Character.getNumericValue(line.charAt(y * SUBWIDTH + x));

                    if (num != 0 && cell.getValue() == 0) {
                        board.setCell(cellY, cellX, num, CellType.GIVEN);
                    } else if (cell.getType() == CellType.INACTIVE) {
                        board.setCell(cellY, cellX, 0, CellType.EMPTY);
                    }
                }
            }

            for (int y = 0; y < SUBHEIGHT / BOXHEIGHT; y++) {
                for (int x = 0; x < SUBWIDTH / BOXWIDTH; x++) {
                    CellHolder box = new CellHolder();
                    boxes.add(box);

                    for (int k = 0; k < BOXHEIGHT; k++) {
                        for (int l = 0; l < BOXWIDTH; l++) {
                            int cellY = (y * BOXHEIGHT + k) + yOffset;
                            int cellX = (x * BOXWIDTH + l) + xOffset;
                            Cell cell = board.getCell(cellY, cellX);
                            box.addCell(cell);
                        }
                    }
                }
            }
        }

        board.setColumns(cols);
        board.setRows(rows);
        board.setBoxes(boxes);

        return board;
    }
}
