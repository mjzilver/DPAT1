package board;

import java.util.ArrayList;

import board.composite.BoardComponent;
import visitor.BoardVisitor;

public class CellHolder implements BoardComponent {
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

    @Override
    public void uncheck() {
        for (Cell cell : cells) {
            cell.uncheck();
        }
    }

    @Override
    public void debugPrint() {
        for (Cell cell : cells) {
            cell.debugPrint();
        }
        System.out.println();
    }
}

