package board;

import java.util.ArrayList;

import board.composite.BoardComponent;
import observer.Observable;
import visitor.BoardVisitor;

public class Board implements Observable, BoardComponent {
    private ArrayList<CellHolder> rows = new ArrayList<>();
    private ArrayList<CellHolder> columns = new ArrayList<>();
    private ArrayList<CellHolder> boxes = new ArrayList<>();
    private Cell[][] cells;
    private Cell selectedCell = null;

    private final int width;
    private final int height;
    private final int boxHeight;
    private final int boxWidth;
    private final int maxNumber;

    public Board(int width, int height, int boxWidth, int boxHeight, int maxNumber) {
        this.width = width;
        this.height = height;
        this.maxNumber = maxNumber;
        this.boxHeight = boxHeight;
        this.boxWidth = boxWidth;

        cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = new Cell(0);
                cells[y][x] = cell;
            }
        }
        selectCell(0, 0);
    }

    public int getBoxIndex(int y, int x) {
        Cell findCell = getCell(y, x);
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = 0; j < boxes.get(i).getCells().size(); j++) {
                if (boxes.get(i).getCells().get(j) == findCell) {
                    return i;
                }
            }
        }
        return 0;
    }

    public Cell getCell(int y, int x) {
        return cells[y][x];
    }

    public void setCell(int y, int x, int number, CellType type) {
        setCell(getCell(y, x), number, type);
    }

    public void setSelectedCell(int number, CellType type) {
        if(getSelectedCell() != null)
            setCell(getSelectedCell(), number, type);
    }

    public void setCell(Cell cell, int number, CellType type) {
        if (number > maxNumber)
            return;

        // You cant edit the given start numbers
        if (cell.getType() == CellType.GIVEN)
            return;

        if (cell.getValue() == number) {
            cell.emptyCell();
            cell.setValue(0);
        } else {
            cell.setValue(number);
            cell.setType(type);
        }
        cell.setStatus(CellStatus.UNCHECKED);
        notifyObservers();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBoxHeight() {
        return boxHeight;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public ArrayList<CellHolder> getBoxes() {
        return boxes;
    }

    public ArrayList<CellHolder> getColumns() {
        return columns;
    }

    public ArrayList<CellHolder> getRows() {
        return rows;
    }

    public void setRows(ArrayList<CellHolder> rows) {
        this.rows = rows;
    }

    public void setColumns(ArrayList<CellHolder> columns) {
        this.columns = columns;
    }

    public void setBoxes(ArrayList<CellHolder> boxes) {
        this.boxes = boxes;
    }

    @Override
    public void accept(BoardVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void uncheck() {
        for (CellHolder row : rows) {
            row.uncheck();
        }
    }

    @Override
    public void debugPrint() {
        for (CellHolder row : rows) {
            row.debugPrint();
        }
    }

    public void selectCell(int cellY, int cellX) {
        selectedCell = getCell(cellY, cellX);
    }
    
    public Cell getSelectedCell() {
        return selectedCell;
    }
}
