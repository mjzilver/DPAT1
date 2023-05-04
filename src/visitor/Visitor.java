package visitor;

import java.util.ArrayList;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellStatus;

public class Visitor {
    public boolean checkBoard(Board board) {
        boolean returnvVal = checkList(board.getBoxes());
        if(!checkList(board.getColumns())) returnvVal = false;
        if(!checkList(board.getRows())) returnvVal = false;
        return returnvVal;
    }

    public boolean checkList(ArrayList<CellHolder> list) {
        boolean returnVal = true;

        for (CellHolder cellHolder : list) {
            if (!checkCells(cellHolder)) returnVal = false;
        }
        return returnVal;
    }

    protected boolean checkCells(CellHolder cellHolder) {
        boolean returnVal = true;
        boolean[] used = new boolean[9];
        for (Cell cell : cellHolder.getCells()) {
            if (cell.getValue() != 0) {
                if (used[cell.getValue() - 1]) {
                    cell.setStatus(CellStatus.WRONG);
                    returnVal = false;
                } else {
                    if (cell.getStatus() == CellStatus.UNCHECKED) {
                        cell.setStatus(CellStatus.CORRECT);
                    }
                    used[cell.getValue() - 1] = true;
                }
            }
        }
        return returnVal;
    }

    public void uncheckBoard(Board board) {
        board.getRows().forEach(cellHolder -> cellHolder.getCells().forEach(cell -> cell.setStatus(CellStatus.UNCHECKED)));
    }
}
