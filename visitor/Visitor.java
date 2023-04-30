package visitor;

import java.util.ArrayList;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellStatus;

public class Visitor {
    public boolean checkBoard(Board board) {
        return (checkList(board.getBoxes())
                && checkList(board.getColumns())
                && checkList(board.getRows()));
    }

    public boolean checkList(ArrayList<CellHolder> list) {
        boolean returnval = true;
        for (CellHolder cellHolder : list) {
            if (!checkCells(cellHolder)) {
                returnval = false;
            }
        }
        return returnval;
    }

    protected boolean checkCells(CellHolder cellHolder) {
        boolean returnval = true;
        boolean[] used = new boolean[9];
        for (Cell cell : cellHolder.getCells()) {
            if (cell.getValue() != 0) {
                if (used[cell.getValue() - 1]) {
                    cell.setStatus(CellStatus.WRONG);
                    returnval = false;
                } else {
                    cell.setStatus(CellStatus.CORRECT);
                    used[cell.getValue() - 1] = true;
                }
            }
        }
        return returnval;
    }
}
