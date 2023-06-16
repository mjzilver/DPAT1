package board;

import java.util.ArrayList;

import visitor.BoardElement;
import visitor.BoardVisitor;

public class CellHolder implements BoardElement {
    private final ArrayList<Cell> cells = new ArrayList<Cell>();
    
    public Cell get(int i) {
        return cells.get(i);
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
    
    @Override
    public void accept(BoardVisitor visitor) {
        visitor.visit(this);
    }
}

