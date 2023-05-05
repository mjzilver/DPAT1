package board;

import java.util.ArrayList;

import observer.Observable;

public class Board implements Observable {
    private final ArrayList<CellHolder> rows = new ArrayList<>();
    private final ArrayList<CellHolder> columns = new ArrayList<>();
    private final ArrayList<CellHolder> boxes = new ArrayList<>();

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

    public Board(int width, int height, int boxWidth, int boxHeight) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < height; i++) {
            CellHolder row = new CellHolder();
            rows.add(row);

            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(0);
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
        for (int i = 0; i < boxHeight - 1; i++) {
            for (int j = 0; j < boxWidth - 1; j++) {
                CellHolder box = new CellHolder();
                boxes.add(box);

                for (int k = 0; k < boxHeight - 1; k++) {
                    CellHolder row = rows.get(i * boxHeight + k);
                    for (int l = 0; l < boxWidth - 1; l++) {
                        Cell cell = row.get(j * boxWidth + l);
                        box.addCell(cell);
                    }
                }
            }
        }
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

    public Cell getCell(int y, int x) {
        return rows.get(y).get(x);
    }

    public void setCell(int y, int x, int number, CellType type) {
        Cell cell = getCell(y, x);
        // You cant edit the given start numbers
        if(cell.getType() == CellType.KNOWN)
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
