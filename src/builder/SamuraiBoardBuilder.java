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
        ArrayList<Integer> nums = new ArrayList<>();
        for (String line : fileContent) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                nums.add(Character.getNumericValue(c));
            }
        }

        int index = 0;

        // Fill the main grids
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if(index >= nums.size()) {
                    index++;
                    System.out.println(index);
                    // this is most likely due to the overlapping not being accounted for yet
                } else  {
                    board.setCell(y, x, nums.get(index++), CellType.GIVEN);
                } 
            }
        }

        return board;
    }
}
