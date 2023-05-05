package board;

import java.util.ArrayList;

public class CellHolder {
    private final ArrayList<Cell> cells = new ArrayList<Cell>();
    
    public Cell get(int i) {
        return cells.get(i);
    }

    public Cell getCell(int i) {
        return cells.get(i);
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
}

