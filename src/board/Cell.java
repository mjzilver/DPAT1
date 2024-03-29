package board;

import java.util.ArrayList;

import board.composite.BoardComponent;
import visitor.BoardVisitor;

public class Cell implements BoardComponent{
    private int value = 0;
    private CellStatus status = CellStatus.UNCHECKED;
    private CellType type = CellType.EMPTY;
    private ArrayList<Integer> possibleValues = new ArrayList<>();

    public Cell(int value) {
        this.value = value;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
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

    public void addPossibleValue(int value) {
        if (possibleValues.contains(value)) {
            removePossibleValue(value);
        } else {
            possibleValues.add(value);
        }
    }

    public void removePossibleValue(int value) {
        possibleValues.remove(Integer.valueOf(value));
    }

    public String getPossibleValues() {
        StringBuilder sb = new StringBuilder();
        possibleValues.sort(Integer::compareTo);
        for (int i = 0; i < possibleValues.size(); i++) {
            sb.append(possibleValues.get(i));
        }                

        return sb.toString();
    }

    @Override
    public void accept(BoardVisitor visitor) {
        // nothing here
    }

    @Override
    public void uncheck() {
        setStatus(CellStatus.UNCHECKED);
    }

    @Override
    public void debugPrint() {
        System.out.print(value + " ");
    }
}
