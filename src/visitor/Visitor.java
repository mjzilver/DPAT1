package visitor;

import java.util.ArrayList;
import java.util.HashMap;

import board.Board;
import board.Cell;
import board.CellHolder;
import board.CellStatus;
import board.CellType;

public class Visitor {
    public boolean checkBoard(Board board) {
        System.out.println("Checking board");
        System.out.println(board.getColumns().size());


        boolean returnvVal = true;
        if (!checkList(board.getBoxes())) returnvVal = false;
        if (!checkList(board.getColumns())) returnvVal = false;
        if (!checkList(board.getRows())) returnvVal = false; 
        return returnvVal;
    }

    public boolean checkList(ArrayList<CellHolder> list) {
        boolean returnVal = true;

        for (CellHolder cellHolder : list) {
            if (!checkCells(cellHolder))
                returnVal = false;
        }
        return returnVal;
    }

    protected boolean checkCells(CellHolder cellHolder) {
        boolean returnVal = true;
        HashMap<Integer, Cell> map = new HashMap<Integer, Cell>();

        for (Cell cell : cellHolder.getCells()) {
            if (cell.getValue() != 0 && cell.getType() != CellType.HELPER) {
                if (map.containsKey(cell.getValue())) {
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

    public void uncheckBoard(Board board) {
        board.getRows()
                .forEach(cellHolder -> cellHolder.getCells().forEach(cell -> cell.setStatus(CellStatus.UNCHECKED)));
    }
}
