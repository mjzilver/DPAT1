package board;

import java.util.ArrayList;

public class Board {
    private ArrayList<CellHolder> rows = new ArrayList<CellHolder>();
    private ArrayList<CellHolder> columns = new ArrayList<CellHolder>();
    private ArrayList<CellHolder> box = new ArrayList<CellHolder>();

    private int width = 0;
    private int height = 0;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < height; i++) {
            CellHolder row = new CellHolder();
            rows.add(row);
            
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(0, false);
                row.addCell(cell);

                
                if (columns.size() <= j) {
                    CellHolder newColumn = new CellHolder();
                    columns.add(newColumn);
                }
                
                // Add the cell to the column
                CellHolder currentColumn = columns.get(j);
                currentColumn.addCell(cell);
            }
        }
    }

    public ArrayList<CellHolder> getRows() {
        return rows;
    }
}
