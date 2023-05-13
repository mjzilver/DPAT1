package builder;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellType;

import java.util.ArrayList;
import java.util.List;

public class SamuraiBoardBuilder implements IBoardBuilder {
    /**
     *
     */
    private static final int width = 21;
    private static final int height = 21;
    private static final int subWidth = 9;
    private static final int subHeight = 9;
    private static final int boxWidth = 3;
    private static final int boxHeight = 3;
    private static final int grids = 5;
    private static final int maxNumber = 9;

    @Override
    public Board build(List<String> fileContent) {
        Board board = new Board(width, height, maxNumber); // 21x21 board with 3x3 boxes

        ArrayList<CellHolder> rows = new ArrayList<>();
        ArrayList<CellHolder> cols = new ArrayList<>();
        ArrayList<CellHolder> boxes = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            CellHolder row = new CellHolder();
            rows.add(row);

            for (int j = 0; j < width; j++) {
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
        for (int i = 0; i < height / boxHeight; i++) {
            for (int j = 0; j < width / boxWidth; j++) {
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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
