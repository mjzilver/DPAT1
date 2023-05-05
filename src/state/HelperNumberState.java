package state;

import board.Cell;
import board.CellType;
import engine.Sudoku;
import view.BaseView;

public class HelperNumberState extends BaseState {
    public HelperNumberState(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    protected void handleNumber(int number) {
        if (number == 0) {
            return;
        }

        BaseView view = sudoku.getView();
        Cell cell = sudoku.getBoard().getCell(view.getSelectedCellY(), view.getSelectedCellX());

        if (cell.getType() == CellType.FINAL || cell.getType() == CellType.GIVEN) {
            return;
        }
        cell.addPossibleValue(number);
        cell.setType(CellType.HELPER);
        sudoku.getBoard().notifyObservers();
    }
}
