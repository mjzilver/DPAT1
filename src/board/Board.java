package board;

import java.util.ArrayList;

import observer.Observable;
import visitor.BoardElement;
import visitor.BoardVisitor;

public class Board implements Observable, BoardElement {
    private ArrayList<CellHolder> rows = new ArrayList<>();
    private ArrayList<CellHolder> columns = new ArrayList<>();
    private ArrayList<CellHolder> boxes = new ArrayList<>();
    private Cell[][] cells;

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
        if (number > maxNumber)
            return;

        Cell cell = getCell(y, x);
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

    public void uncheckBoard() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                cells[y][x].setStatus(CellStatus.UNCHECKED);
            }
        }
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
}
