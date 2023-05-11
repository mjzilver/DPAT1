package board;

import java.util.ArrayList;

import observer.Observable;

public class Board implements Observable {
    private ArrayList<CellHolder> rows = new ArrayList<>();
    private ArrayList<CellHolder> columns = new ArrayList<>();
    private ArrayList<CellHolder> boxes = new ArrayList<>();

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

    public Cell getCell(int y, int x) {
        return rows.get(y).get(x);
    }

    public void setCell(int y, int x, int number, CellType type) {
        if (number > maxNumber) 
            return;
        
        Cell cell = getCell(y, x);
        // You cant edit the given start numbers
        if(cell.getType() == CellType.GIVEN)
            return;

        if (cell.getValue() == number) {
            cell.emptyCell();
            cell.setValue(0);
        } else {
            cell.setValue(number);
            cell.setType(type);
        }
        notifyObservers();
    }
}
