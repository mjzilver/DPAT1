package visitor;

import java.util.ArrayList;
import java.util.HashMap;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellStatus;
import board.CellType;

public class CheckVisitor implements BoardVisitor {
    @Override
    public boolean visit(Board board) {
        boolean returnvVal = true;
        if (!visit(board.getBoxes())) returnvVal = false;
        if (!visit(board.getColumns())) returnvVal = false;
        if (!visit(board.getRows())) returnvVal = false; 
        return returnvVal;
    }

    public boolean visit(ArrayList<CellHolder> list) {
        boolean returnVal = true;

        for (CellHolder cellHolder : list) {
            if (!visit(cellHolder))
                returnVal = false;
        }
        return returnVal;
    }

    @Override
    public boolean visit(CellHolder cellHolder) {
        boolean returnVal = true;
        // puts all the cells in a hashmap with the value as the key
        HashMap<Integer, Cell> map = new HashMap<Integer, Cell>();

        for (Cell cell : cellHolder.getCells()) {
            if (cell.getValue() != 0 && cell.getType() != CellType.HELPER) {
                // if the value is already in the hashmap
                if (map.containsKey(cell.getValue())) {
                    // if the cell is a given cell, then the other cell is wrong
                    if (cell.getType() == CellType.GIVEN) {
                        map.get(cell.getValue()).setStatus(CellStatus.WRONG);
                    } else {
                        cell.setStatus(CellStatus.WRONG);
                    }
                    returnVal = false;
                } else {
                    if (cell.getStatus() == CellStatus.UNCHECKED) {
                        cell.setStatus(CellStatus.CORRECT);
                    }
                    map.put(cell.getValue(), cell);
                }
            }
        }
        return returnVal;
    }
}
