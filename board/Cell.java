package board;

public class Cell {
    private int value = 0;
    private boolean isFinal = false;

    public Cell(int value, boolean isFinal) {
        this.value = value;
        this.isFinal = isFinal;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
}
