package board;

import java.util.ArrayList;

import observer.Observable;

public class Board implements Observable {
    private ArrayList<CellHolder> rows = new ArrayList<CellHolder>();
    private ArrayList<CellHolder> columns = new ArrayList<CellHolder>();
    private ArrayList<CellHolder> box = new ArrayList<CellHolder>();

    private int width = 0;
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private int height = 0;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < height; i++) {
            CellHolder row = new CellHolder();
            rows.add(row);
            
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(0, false);
                row.addCell(cell);

                
                if (columns.size() <= j) {
                    CellHolder newColumn = new CellHolder();
                    columns.add(newColumn);
                }
                
                // Add the cell to the column
                CellHolder currentColumn = columns.get(j);
                currentColumn.addCell(cell);
            }
        }

        // filling the boxes 3x3's inside the 9x9
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                CellHolder boxCells = new CellHolder();
                
                for (int row = boxRow * 3; row < boxRow * 3 + 3; row++) {
                    for (int col = boxCol * 3; col < boxCol * 3 + 3; col++) {
                        boxCells.addCell(getCell(row, col));
                    }
                }
    
                box.add(boxCells);
            }
        }
    }

    public ArrayList<CellHolder> getRows() {
        return rows;
    }

    public Cell getCell(int y, int x) {
        return rows.get(y).get(x);
    }

    public void setCell(int y, int x, int number) {
        getCell(y, x).setValue(number);
        notifyObservers();
    }

    public void setCell(int y, int x, int number, boolean isFinal) {
        Cell cell = getCell(y, x); 
        cell.setValue(number);
        cell.setFinal(isFinal);
        notifyObservers();
    }
}
