package board;

public class Cell {
    private int value = 0;
    private CellStatus status = CellStatus.UNCHECKED;
    private CellType type = CellType.EMPTY;

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void emptyCell() {
        this.value = 0;
        this.type = CellType.EMPTY;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
