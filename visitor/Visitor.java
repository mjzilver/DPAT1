package visitor;

import java.util.ArrayList;

import board.Cell;
import board.CellHolder;

public class Visitor {
    public boolean checkHolder(CellHolder cellHolder) {
        return checkCells(cellHolder);
    }

    public boolean checkList(ArrayList<CellHolder> boxes) {
        for (CellHolder cellHolder : boxes) {
            if(!checkHolder(cellHolder)) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkCells(CellHolder cellHolder) {
        boolean[] used = new boolean[9];
        for (Cell cell : cellHolder.getCells()) {
            if (cell.getValue() != 0) {
                if (used[cell.getValue()-1]) {
                    return false;
                } else {
                    used[cell.getValue()-1] = true;
                }
            }
        }
        return true;
    }
}
