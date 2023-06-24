import board.Cell;
import board.CellStatus;
import board.CellType;

import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void testCellCreation() {
        Cell cell = new Cell(0);
        assertEquals(cell.getValue(), 0);
        assertEquals(cell.getType(), CellType.EMPTY);
    }

    @Test
    public void testCellSetType() {
        Cell cell = new Cell(0);
        cell.setType(CellType.GIVEN);
        assertEquals(cell.getType(), CellType.GIVEN);
    }

    @Test
    public void testCellSetValue() {
        Cell cell = new Cell(0);
        cell.setValue(1);
        assertEquals(cell.getValue(), 1);
    }

    @Test
    public void testCellEmptyCell() {
        Cell cell = new Cell(0);
        cell.setValue(1);
        cell.setType(CellType.GIVEN);
        cell.emptyCell();
        assertEquals(cell.getValue(), 0);
        assertEquals(cell.getType(), CellType.EMPTY);
    }

    @Test
    public void testCellSetStatus() {
        Cell cell = new Cell(0);
        cell.setStatus(CellStatus.CORRECT);
        assertEquals(cell.getStatus(), CellStatus.CORRECT);
    }

    @Test
    public void testCellAddPossibleValue() {
        Cell cell = new Cell(0);
        cell.addPossibleValue(5); // add
        cell.addPossibleValue(5); // remove
        cell.addPossibleValue(5); // add again
        cell.addPossibleValue(4); // add
        cell.addPossibleValue(1); // add
        cell.addPossibleValue(3); // add
        cell.addPossibleValue(1); // remove

        assertEquals(cell.getPossibleValues(), "345");
    }
}
